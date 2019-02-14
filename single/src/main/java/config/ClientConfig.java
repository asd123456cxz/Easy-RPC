package config;

import connector.ClientConnection;
import demo.DemoApiImpl;
import export.URL;
import interf.DemoApi;
import invoker.ClientInvoker;
import invoker.Invoker;
import processor.Processor;
import processor.RegistryProcessor;
import processor.ServerProcessor;
import proxy.ClientProxy;
import proxy.ProxyFactory;
import registry.RegistryConfig;
import spi.SpiLoader;
import util.GlobalConstant;
import Exception.RpcException;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ClientConfig<T> {

    private Class<?> interf;

    private RegistryConfig registryConfig;

    private String serviceName;

    private final static Processor processor =new RegistryProcessor(new ServerProcessor());
    //private final static Processor processor = new SpiLoader<Processor>(Processor.class).loadExtension("");

    private ProxyFactory proxy = new SpiLoader<ProxyFactory>(ProxyFactory.class).loadExtension(GlobalConstant.PROXY_MODE_JDK);;

    private ClientConnection connection;
    //----------Constructor------------
    public ClientConfig() {}

    public ClientConfig(int port) {
        connection= new ClientConnection(port);
    }

    //----------Method------------
    public T call(){
        //1.检验
        checkBeforeRefer();
        //2.构建通信所需的url

        checkBeforeURL();
        URL url=new URL(serviceName,registryConfig,interf);
        //3.通过通信获取必要信息构建invoker
        Invoker<T> invoker = processor.refer(interf,url);
        return (T)proxy.getProxy(invoker);
    }

    private void checkBeforeURL() {
    }

    private void checkBeforeRefer() {
        if(!interf.isInterface()){
            throw new RpcException("target is not interface");
        }

    }

    //----------Getter/Setter------------
    public void setInterf(Class<?> tClass){
        this.interf=tClass;
    }

    public Class<?> getInterf() {
        return interf;
    }

    public RegistryConfig getRegistryConfig() {
        return registryConfig;
    }

    public void setRegistryConfig(RegistryConfig registryConfig) {
        this.registryConfig = registryConfig;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
