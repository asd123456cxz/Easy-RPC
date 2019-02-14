package invoker;

import export.URL;
import util.BaseUtil;
import util.RpcContext;
import connector.Connection;
import connector.Transporter;

import java.lang.reflect.InvocationTargetException;

public class ClientInvoker implements Invoker {

    private URL url;

    private Connection connection;

    public ClientInvoker(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Object invoke(Invocation invocation) {
        //TODO 区分同步/异步有返回值/异步无返回值
        Transporter transporter = BaseUtil.Invo2Tran(invocation);
        String returnType = "";
        if("oneway".equalsIgnoreCase(returnType)){
            //异步无返回值 返回一个空
            connection.send(transporter);
            return transporter;
        }else if("isAsync".equalsIgnoreCase(returnType)){
            //异步有返回值 设置到全局上下文中 返回一个空
            transporter = connection.request(transporter);
            RpcContext.setResult(transporter);
            return transporter;
        }else{
            //同步 直接返回
            transporter=connection.request(transporter);
            return transporter.get();
        }
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }
}
