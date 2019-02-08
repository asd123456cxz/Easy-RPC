package demo;

import config.ServiceConfig;

public class DemoService {



    public static void main(String[] args) {
        ServiceConfig config = new ServiceConfig(8088);
        //1.暴露服务（injvm）
        //2.开启socket连接

    }

}
