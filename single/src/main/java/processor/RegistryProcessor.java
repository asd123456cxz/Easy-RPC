package processor;

import export.Exporter;
import export.URL;
import invoker.Invoker;
import invoker.ServiceInvoker;
import processor.Processor;
import registry.Registry;
import registry.RegistryConfig;
import registry.ZookeeperRegistry;

import javax.crypto.spec.IvParameterSpec;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import Exception.RpcException;
/*
    最底层服务域
 */
public class RegistryProcessor implements Processor {

    private Registry registry;

    private Processor processor;

    public RegistryProcessor() {}

    public RegistryProcessor(Processor processor) {
        this.processor = processor;
    }

    @Override
    public Exporter export(Invoker invoker) {
        try {
            ServiceInvoker serviceInvoker;
            if(invoker instanceof ServiceInvoker) {
                serviceInvoker = (ServiceInvoker) invoker;
                registry=getRegistry(serviceInvoker.getUrl());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(Objects.isNull(registry)){
            throw new RpcException("not found suitble registry");
        }
        //注册
        registry.regist();
        Class<? extends Invoker> aClass = invoker.getClass();
        //最后返回一个exporter
         return new Exporter(invoker);
    }

    private Registry getRegistry(URL url) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        ServiceInvoker serviceInvoker;
        Registry registry=null;
        RegistryConfig registryConfig = url.getRegistryConfig();
        Class<? extends Registry> center = registryConfig.getRegistryCenter();
        Constructor[] constructors = center.getDeclaredConstructors();
        for(Constructor c:constructors){
            if(c.getParameterCount()>0){
                Class[] types = c.getParameterTypes();
                Param:for(Class t:types){
                    if(t.isAssignableFrom(URL.class)){
                        break Param;
                    }
                }
                registry= (Registry)c.newInstance(url);
                break;
            }
        }
        return registry;
    }


    @Override
    public <T> Invoker<T> refer(Class<?> clazz , URL url) {
        //从注册中心对应的地址拿到服务端地址
        Registry registry=null;
        try {
            registry = getRegistry(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //192.168.96.1:8000
        String address = registry.getAddress();
        address="192.168.96.1:8000";
        String[] split = address.split(":");
        url.setHost(split[0]);
        url.setPort(Integer.valueOf(split[1]));
        //调用通信processor
        Invoker<T> invoker = processor.refer(clazz, url);
        return invoker;
    }
}
