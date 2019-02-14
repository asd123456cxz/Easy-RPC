package demo;


import config.ClientConfig;
import interf.DemoApi;
import registry.RegistryConfig;

public class DemoClient {

    public static void main(String[] args) {
        ClientConfig<DemoApi> config = new ClientConfig<>();
        config.setInterf(DemoApi.class);
        config.setRegistryConfig(new RegistryConfig("zookeeper://111.11.11.11:2171"));
        config.setServiceName("demoApiImpl");

        DemoApi api = config.call();
        api.hello1("rpc");
    }

}
