package proxy;

import export.URL;
import invoker.Invoker;

public interface ProxyFactory {

    //目前是用于consumer端包装invoker
    <T> T getProxy(Invoker<T> invoker);
    //用于service端
    <T> Invoker<T> getInvoker(T instance, URL url);

}
