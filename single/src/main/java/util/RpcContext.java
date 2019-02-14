package util;

import connector.Transporter;

import java.util.concurrent.ConcurrentHashMap;

public class RpcContext {

    private static ConcurrentHashMap<String,Transporter> resultMap = new ConcurrentHashMap();

    public static void setResult(Transporter transporter){
        resultMap.put(transporter.getServiceName(),transporter);
    }

    public static void getResult(String serviceName){
        resultMap.get(serviceName);
    }

}
