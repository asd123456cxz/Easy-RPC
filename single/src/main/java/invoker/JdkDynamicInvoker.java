package invoker;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import Exception.RpcException;
import export.URL;

public class JdkDynamicInvoker implements InvocationHandler {

    private Invoker<?> invoker;

    public JdkDynamicInvoker(Invoker<?> invoker) {
        this.invoker = invoker;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Invocation invocation=buildInvocation(invoker,method,args);
        return invoker.invoke(invocation);
    }

    private Invocation buildInvocation(Invoker<?> invoker, Method method, Object[] args) {
        ClientInvoker clientInvoker =null;
        if(invoker instanceof ClientInvoker){
            clientInvoker=(ClientInvoker)invoker;
        }else{
            throw new RpcException("illegal invoker");
        }
        URL url = clientInvoker.getUrl();

        Class<?>[] argsType=null;
        if(args!=null){
            argsType=new Class[args.length];
            for(int i=0;i<args.length;i++){
                Class<?> argClass = args[i].getClass();
                argsType[i]=argClass;
            }
        }

        return new Invocation(url.getServiceName(),url.getClazz(),method.getName(),args,argsType);
    }
}
