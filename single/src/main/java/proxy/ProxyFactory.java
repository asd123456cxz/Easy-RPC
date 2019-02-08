package proxy;

public abstract class AbstractProxyFactory<T> {

    abstract T getProxy(Class<?> classType);

}
