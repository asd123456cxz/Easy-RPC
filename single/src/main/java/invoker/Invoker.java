package invoker;

import java.lang.reflect.InvocationTargetException;

public interface Invoker<T> {

    Object invoke(Invocation invocation) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException;

}
