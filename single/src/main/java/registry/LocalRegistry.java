package registry;

import export.URL;

import java.util.HashMap;
import java.util.HashSet;

public class LocalRegistry {

    public static HashSet<URL> urls = new HashSet<>();

    public static HashMap<Class,Object> container = new HashMap<>();

    public void regist(URL url , Object instance) {
        urls.add(url);
        container.put(url.getClazz(),instance);
    }

    public static Object getInstance(Class clazz){
        return container.get(clazz);
    }

    public static boolean contain(URL url){
        return urls.contains(url);
    }
}
