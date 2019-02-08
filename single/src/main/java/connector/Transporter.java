package connector;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Protocal<T> {

    private Class<T> classname;

    private Method method;

    private Object[] args;

    public Protocal(Class<T> classname, Method method, Object[] args) {
        this.classname = classname;
        this.method = method;
        this.args = args;
    }

    public Class getClassname() {
        return classname;
    }

    public void setClassname(Class classname) {
        this.classname = classname;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return "Protocal{" +
                "classname=" + classname +
                ", method=" + method +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
