package invoker;

import java.lang.reflect.Method;

public class Invocation {

    private String serviceName;

    private Class clazz;

    private String methodName;

    private Object[] args;

    private Class<?>[] argTypes;

    public Invocation(String serviceName, Class clazz, String methodName, Object[] args, Class<?>[] argTypes) {
        this.serviceName = serviceName;
        this.clazz = clazz;
        this.methodName = methodName;
        this.args = args;
        this.argTypes = argTypes;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public Class<?>[] getArgTypes() {
        return argTypes;
    }

    public void setArgTypes(Class<?>[] argTypes) {
        this.argTypes = argTypes;
    }
}
