package connector;

import export.ExporterCache;
import invoker.Invoker;
import registry.LocalRegistry;
import util.BaseUtil;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class ServiceConnection implements Connection{

    private String host;

    private int port;

    private Transporter transporter;

    public ServiceConnection() {
    }

    public ServiceConnection(int port) {
        this.port=port;
    }

    public void openServer(){
        ServerSocket server =null;
        Socket socket = null;
        try {
            server = new ServerSocket(port);
            System.out.println("已开启："+"端口："+port);
            while(true){
                if((socket=server.accept())==null){
                    continue;
                }
                byte[] decode = new byte[1024];
                InputStream inputStream = socket.getInputStream();
                inputStream.read(decode);
                Transporter transporter = deserialize(decode, Transporter.class);

                //调用并返回结果
                //移交给服务域找到invoker并执行invoke
                String serviceName = transporter.getServiceName();
                String serviceSign = host+":" +port + "/" + serviceName;
                Invoker<?> invoker = ExporterCache.getCache(serviceSign).getInvoker();
                Object o =invoker.invoke(BaseUtil.Tran2Invo(transporter));
                transporter.setReturnValue(o);
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write(serialize(transporter));
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } finally {
            try {
                server.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void send(Transporter transporter) {

    }

    @Override
    public Transporter request(Transporter transporter) {
        return null;
    }

    @Override
    public void run() {
        openServer();
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
