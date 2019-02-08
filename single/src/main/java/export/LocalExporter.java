package export;

import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

public class Exporter {

    private ConcurrentHashMap<Class,HashSet<String>> relation = new ConcurrentHashMap<>();

    public Exporter(Class clazz ,HashSet<String> methods){
        relation.put(clazz,methods);
    }

}
