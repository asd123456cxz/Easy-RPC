package processor;

import ThreadPool.CommonThreadPool;
import connector.ClientConnection;
import connector.Connection;
import connector.ServiceConnection;
import connector.Transporter;
import export.Exporter;
import export.LocalExporter;
import export.URL;
import invoker.ClientInvoker;
import invoker.Invocation;
import invoker.Invoker;
import invoker.ServiceInvoker;
import io.netty.channel.Channel;
import registry.Registry;
import registry.RegistryConfig;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;


public class ServerProcessor implements Processor{

    private Connection connection;

    private Processor processor;

    private List<Invoker> invokers = new ArrayList<>();

    public ServerProcessor() {
    }

    public ServerProcessor(Processor processor) {
        this.processor = processor;
    }

    @Override
    public Exporter export(Invoker invoker) {
        ServiceInvoker serviceInvoker;
        if(invoker instanceof ServiceInvoker) {
            //新建连接
            serviceInvoker = (ServiceInvoker) invoker;
            URL url = serviceInvoker.getUrl();
            int port = url.getPort();
            connection=new ServiceConnection(port);
            ((ServiceConnection) connection).setHost(url.getHost());
            //移交线程池处理
            ThreadPoolExecutor executor = CommonThreadPool.getDefault();
            executor.execute(connection);
        }
        invokers.add(invoker);
        return processor.export(invoker);
    }

    @Override
    public <T> Invoker<T> refer(Class<?> clazz, URL url) {
        //需要发送一个 形如 "192.168.96.1:8000/DemoService" 服务地址+serviceName
        //新建连接
        String host = url.getHost();
        int port = url.getPort();
        connection=new ClientConnection(port);
        ((ClientConnection) connection).setHost(host);
        ClientInvoker invoker = new ClientInvoker(connection);
        invoker.setUrl(url);
        return invoker;
    }

    public Object reply(Channel channel){

        return null;
    }

}
