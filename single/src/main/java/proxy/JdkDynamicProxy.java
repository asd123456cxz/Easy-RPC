package proxy;

import export.URL;
import invoker.ClientInvoker;
import invoker.Invoker;
import invoker.JdkDynamicInvoker;
import invoker.ServiceInvoker;

import java.io.UncheckedIOException;
import java.lang.reflect.Proxy;

@SuppressWarnings({"unchecked", "rawtypes"})
public class JdkDynamicProxy implements ProxyFactory {

    @Override
    public <T> T getProxy(Invoker<T> invoker) {
        invoker=(ClientInvoker)invoker;
        Class clazz = ((ClientInvoker) invoker).getUrl().getClazz();
        return (T)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),new Class[]{clazz},new JdkDynamicInvoker(invoker));
    }

    @Override
    public <T> Invoker<T> getInvoker(T instance , URL url) {
        return new ServiceInvoker(instance,url);
    }
}
