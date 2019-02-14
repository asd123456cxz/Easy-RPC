package proxy;

import registry.LocalRegistry;
import connector.ClientConnection;
import connector.Transporter;
import export.Exporter;
import export.URL;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import Exception.RpcException ;

public class ClientProxy <T> implements InvocationHandler {

    private Class<T> tClass;

    private int port;

    public ClientProxy(int port){
        this.port=port;
    }

    public T getInvoker(Class<T> tClass){
        this.tClass=tClass;
        return (T)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),new Class[]{tClass},this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        /*URL url = new URL(URL.MY_PROTOCAL, "",8080,this.tClass);
        if(!LocalRegistry.contain(url))
        {
            throw new RpcException("did not registry yet");
        }*/
        return null;
    }


    public void remoteInvoke(Transporter transporter){
        ClientConnection connection= new ClientConnection(port);
        connection.setTransporter(transporter);
        connection.transfer();
    }

}
