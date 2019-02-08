package invoker;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AbstractInvoker<T> {

    private T instance;

    AbstractInvoker(T instance){
        this.instance=instance;
    }

    public void invoke(Invokation invokation) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method = instance.getClass().getMethod(invokation.getMethodName(),invokation.getArgTypes());
        method.invoke(instance,invokation.getArgs());
    }

}
