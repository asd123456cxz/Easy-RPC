package export;

import registry.RegistryConfig;

import java.util.Map;
import java.util.Objects;

public class URL {

    private String protocal;

    private String host;

    private int port;

    private RegistryConfig registryConfig;

    private Class clazz;

    private Object instance;

    private String serviceName;

    private Map<String,String> arguments;

    public static final String MY_PROTOCAL="SUL";

    public URL(String serviceName,RegistryConfig registryConfig,Class clazz){
        this(MY_PROTOCAL,serviceName,registryConfig,clazz);
    }

    public URL(String protocal,String serviceName,RegistryConfig registryConfig,Class clazz){
        this.protocal = protocal;
        this.serviceName=serviceName;
        this.registryConfig = registryConfig;
        this.clazz=clazz;
    }

    public URL(String serviceName,String host,int port,RegistryConfig registryConfig, Class clazz,Object instance) {
        this(MY_PROTOCAL,serviceName,host,port,registryConfig,clazz,instance);
    }

    public URL(String protocal, String serviceName,String host,int port,RegistryConfig registryConfig , Class clazz ,Object instance) {
        this.protocal = protocal;
        this.serviceName=serviceName;
        this.host=host;
        this.port=port;
        this.registryConfig = registryConfig;
        this.clazz=clazz;
        this.instance=instance;
    }

    public String getServiceSign(){
        return host+":"+port+"/"+ serviceName;
    }

    public String getProtocal() {
        return protocal;
    }

    public void setProtocal(String protocal) {
        this.protocal = protocal;
    }

    public RegistryConfig getRegistryConfig() {
        return registryConfig;
    }

    public void setRegistryConfig(RegistryConfig registryConfig) {
        this.registryConfig = registryConfig;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Object getInstance() {
        return instance;
    }

    public void setInstance(Object instance) {
        this.instance = instance;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
