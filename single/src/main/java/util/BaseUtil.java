package util;

import connector.Transporter;
import invoker.Invocation;

public class BaseUtil {

    public static Transporter Invo2Tran(Invocation invocation){
        return new Transporter(invocation.getServiceName(),invocation.getClazz(),invocation.getMethodName(),invocation.getArgs(),invocation.getArgTypes());
    }

    public static Invocation Tran2Invo(Transporter transporter){
        return new Invocation(transporter.getServiceName(),transporter.getClassname(),transporter.getMethodName(),transporter.getArgs(),transporter.getArgsType());
    }

}
