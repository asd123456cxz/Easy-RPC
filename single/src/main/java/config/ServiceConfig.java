package config;

import annonation.NonRemote;
import annonation.Service;
import connector.ServiceConnection;
import export.Exporter;
import export.ExporterCache;
import export.URL;
import invoker.Invoker;
import org.apache.commons.lang3.StringUtils;
import processor.Processor;
import processor.RegistryProcessor;
import processor.ServerProcessor;
import proxy.ProxyFactory;
import registry.RegistryConfig;
import spi.SpiLoader;
import util.AddressUtil;
import Exception.RpcException;
import util.GlobalConstant;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

public class ServiceConfig<T> {

    private String serviceName;

    private Class interf;

    private String servicePackage;

    private T instance;

    private String host;

    private int port = GlobalConstant.PORT;

    private RegistryConfig registryConfig;

    private HashSet<String> relatedMethods = new HashSet<String>();

    private HashSet<String> nonRemoteMethods = new HashSet<String>();

    //TODO 应该避免在初始化时全部加载
    //private final static Processor processor = new SpiLoader<Processor>(Processor.class).loadExtension("");

    private final static ProxyFactory proxyFactory = new SpiLoader<ProxyFactory>(ProxyFactory.class).loadExtension(GlobalConstant.PROXY_MODE_JDK);

    private static Boolean LOCAL_EXPORT=true;

    public ServiceConfig(){}

    public ServiceConfig(int port) {
        this.port = port;
    }

    public void setRegistryConfig(RegistryConfig registryConfig){
        this.registryConfig = registryConfig;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setClass(Class clazz){
        this.interf=clazz;
    }

    public void setInstance(T instance){
        this.instance=instance;
    }

    public String getServicePackage() {
        return servicePackage;
    }

    public void setServicePackage(String servicePackage) {
        this.servicePackage = servicePackage;
    }

    /*
            1.URL特指通信地址，本机IP加端口  还有一个RegistryUrl代表注册中心地址
            ；默认端口自定义为8000
            2.根据有无RegistryUrl来判断是直连还是通过注册中心
            3.注册中心保存的是URL，供consumer来连接
         */
    public void export(){
        //1.TODO 校验
        /*Method[] methods = interf.getMethods();
        for(Method method : methods){
            //检查不想被注入的方法（注解）
            if(checkNonRemote(method)){
                continue;
            }
            relatedMethods.add(method.getName());
        }*/
        //获取serviceName，从注解中或者默认类名首字母小写
        checkServiceName();
        //远程暴露和本地暴露
        checkBeforeExport();
        if(LOCAL_EXPORT){
            localExport();
        }else{
            //2.拼接url(协议名是个摆设)
            URL url=new URL(serviceName,getHost(),port,registryConfig,interf,instance);
            //3.获取Invoker模型
            Invoker invoker=proxyFactory.getInvoker(instance,url);
            //4.开启通信，执行暴露，注册服务
            //此处构建一个processor链，先执行其他processor，如开启通信，最终执行RegistryProcessor
            //TODO 暂时替代processor
            Exporter exporter = new ServerProcessor(new RegistryProcessor()).export(invoker);
            ExporterCache.cache(exporter);
        }


    }

    private void checkServiceName() {
        if(StringUtils.isEmpty(servicePackage)){
            //首字母小写
            serviceName=StringUtils.uncapitalize(instance.getClass().getSimpleName());
        }else{
            String dir=GlobalConstant.PROJECT_DIR+servicePackage;
            scanForService(dir);
        }
    }

    private void scanForService(String dir) {
        File file = new File(dir);
        File[] files = file.listFiles();
        if(files.length>0){
            for(File f: files){
                if(f.isDirectory()){
                    scanForService(f.getAbsolutePath());
                }
                String fileName = f.getName();
                String className = servicePackage + "." + StringUtils.remove(fileName, ".class");
                if(fileName.indexOf(".class")>0){
                    try {
                        Class<?> clazz = ClassLoader.getSystemClassLoader().loadClass(className);
                        if(interf.isAssignableFrom(clazz)){
                            if(clazz.getAnnotation(Service.class)!=null){
                                Service s=clazz.getAnnotation(Service.class);
                                serviceName=s.value();
                            };
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }

    public void localExport(){
        //new LocalExporter(interf,relatedMethods,registryPort);
    }

    private String getHost(){
        String host=null;
        try {
            host=InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return host;
    }

    private void checkBeforeExport() {
        //确定暴露类型
        if(null!=registryConfig){
            String address = registryConfig.getAddress();
            if(!(StringUtils.isEmpty(address) ||"local".equalsIgnoreCase(address))){
                LOCAL_EXPORT=false;
            }
        }
    }

    public boolean checkNonRemote(Method method) {
        if(nonRemoteMethods.contains(method.getName())){
            return true;
        }
        return method.getAnnotation(NonRemote.class)==null;
    }
}
