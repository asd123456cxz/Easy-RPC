package registry;

import export.URL;

public class ZookeeperRegistry implements Registry {

    private URL url;

    public ZookeeperRegistry(URL url) {
        this.url = url;
    }

    @Override
    public void regist() {
        RegistryConfig registryConfig = url.getRegistryConfig();
        Class clazz = url.getClazz();
        String host = url.getHost();
        int port = url.getPort();
        Object instance = url.getInstance();
        System.out.println(instance.getClass().getName());
    }

    @Override
    public String getAddress() {
        return null;
    }
}
