package registry;

import com.sun.deploy.util.StringUtils;
import Exception.RpcException;
import export.URL;
import util.AddressUtil;

import java.lang.reflect.Constructor;

public class RegistryConfig {

    private String host;

    private int port;

    private String address;

    private Class<? extends Registry> registryCenter;

    private enum RegistryCenter{

        ZK("zookeeper",ZookeeperRegistry.class);

        private String name;
        private Class value;

        RegistryCenter(String name,Class clazz) {
            this.name = name;
            this.value=clazz;
        }

        private String getName() {
            return name;
        }

        public Class getValue() {
            return value;
        }

        public static Class contain(String center){
            boolean flag =false;
            Class clazz=null;
            for(RegistryCenter rc : RegistryCenter.values()){
                flag=rc.getName().equalsIgnoreCase(center);
                if(flag){
                    //必须使用URL为入参的构造器
                    clazz=rc.getValue();
                    break;
                }
            }
            return clazz;
        }
    }

    public RegistryConfig() {
    }

    public RegistryConfig(String address){
        checkAddress(address);
        this.address=address;
    }

    private void checkAddress(String address) {
        String[] strings = StringUtils.splitString(address, "://");
        if(strings.length==3){
            String registryType = strings[0];
            String host = strings[1];
            int port = Integer.valueOf(strings[2]);
            if((registryCenter=RegistryCenter.contain(registryType))!=null){
                //TODO file类型没有处理
                if(AddressUtil.LegalHost(host)
                        && (port<65535 && port>0)){
                    this.host=host;
                    this.port=port;
                    return;
                }
            }
        }
        throw new RpcException("illegal address");
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Class<? extends Registry> getRegistryCenter() {
        return registryCenter;
    }

    public void setRegistryCenter(Class<? extends Registry> registryCenter) {
        this.registryCenter = registryCenter;
    }
}
