package export;

import invoker.ServiceInvoker;

import java.util.concurrent.ConcurrentHashMap;

public class ExporterCache{

    private static ConcurrentHashMap<String,Exporter<?>> cacheMap = new ConcurrentHashMap<>();

    public static void cache(Exporter<?> exporter){
        ServiceInvoker invoker = (ServiceInvoker)(exporter.getInvoker());
        cacheMap.put(invoker.getUrl().getServiceSign(),exporter);
    }

    public static Exporter<?> getCache(String key){
        return cacheMap.get(key);
    }

}
