package proxy;

import export.URL;
import invoker.Invoker;

public class CglibProxy implements ProxyFactory {

    @Override
    public <T> Invoker<T> getInvoker(T instance, URL url) {
        return null;
    }

    @Override
    public <T> T getProxy(Invoker<T> invoker) {
        return null;
    }
}
