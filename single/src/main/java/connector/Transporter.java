package connector;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Transporter<T,R> implements Serializable {

    private String serviceName;

    private Class<T> classname;

    private String methodName;

    private Object[] args;

    private Class<?>[] argsType;

    private Class<R> returnClass;

    private R returnValue;

    public Transporter(String serviceName,Class<T> classname, String methodName, Object[] args , Class<?>[] argsType) {
        this.serviceName=serviceName;
        this.classname = classname;
        this.methodName=methodName;
        this.args = args;
        this.argsType=argsType;
    }

    public Transporter() {}

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Class getClassname() {
        return classname;
    }

    public void setClassname(Class classname) {
        this.classname = classname;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public Class<R> getReturnClass() {
        return returnClass;
    }

    public void setReturnClass(Class<R> returnClass) {
        this.returnClass = returnClass;
    }

    public R getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(R returnValue) {
        this.returnValue = returnValue;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public R get(){
        return returnValue;
    }

    public Class<?>[] getArgsType() {
        return argsType;
    }

    public void setArgsType(Class<?>[] argsType) {
        this.argsType = argsType;
    }
}
