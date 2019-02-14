package demo;

import annonation.Service;
import config.ServiceConfig;
import interf.DemoApi;
import registry.RegistryConfig;
import registry.ZookeeperRegistry;
import util.GlobalConstant;

import java.io.*;

public class DemoService {


    public static void main(String[] args){
        //显式指定port，或不指定,默认8000
        ServiceConfig config = new ServiceConfig();
        //格式可以是"zookeeper://111.11.11.11:2171"
        //如果是不使用注册中心，可以指定为""或"local" 或不指定
        config.setRegistryConfig(new RegistryConfig("zookeeper://111.11.11.11:2171"));
        //手动注入接口等信息/根据自定义的@Service注解扫描自动注入
        config.setClass(DemoApi.class);
        config.setInstance(new DemoApiImpl());
        //如果使用@Service注解就需要写好包名
        //config.setServicePackage("demo");
        config.export();
    }

}
