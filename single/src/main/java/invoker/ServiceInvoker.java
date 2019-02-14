package invoker;

import connector.Connection;
import export.URL;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ServiceInvoker<T> implements Invoker{

    private URL url;

    private T instance;

    public ServiceInvoker(T instance ,URL url){
        this.instance=instance;
        this.url = url ;
    }

    @Override
    public Object invoke(Invocation invocation) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method = instance.getClass().getMethod(invocation.getMethodName(), invocation.getArgTypes());
        return method.invoke(instance, invocation.getArgs());
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }
}
