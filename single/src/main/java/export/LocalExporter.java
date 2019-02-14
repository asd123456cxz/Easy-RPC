package export;

import registry.LocalRegistry;

import java.util.HashSet;
import java.util.Set;

public class LocalExporter {

    private Class clazz;

    private Set<String> methods;

    public static LocalRegistry registry = new LocalRegistry();

    public LocalExporter(Class clazz , HashSet<String> methods,int port){
        this.clazz=clazz;
        this.methods=methods;
        export(port);
    }

    public void export(int port) {
        /*Class[] interfaces = clazz.getInterfaces();
        URL url = new URL(URL.MY_PROTOCAL,"",port,interfaces[0]);
        try {
            Object o = clazz.newInstance();
            registry.regist(url,o);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }*/
    }

}
