package connector;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.caucho.hessian.io.HessianFactory;
import com.caucho.hessian.io.HessianOutput;
import export.Exporter;

import java.io.*;
import java.net.Socket;

public class ClientConnection implements Connection{

    private String host;

    private int port;

    private Transporter transporter;

    public ClientConnection(int port){
        this.port=port;
    }

    public ClientConnection() {

    }

    public Transporter getTransporter() {
        return transporter;
    }

    public ClientConnection setTransporter(Transporter transporter) {
        this.transporter = transporter;
        return this;
    }

    public Transporter transfer(){
        Socket socket = null;
        OutputStream outputStream =null;
        InputStream inputStream = null;
        try {
            socket= new Socket(host,port);
            outputStream = socket.getOutputStream();
            //编码使用hession
            byte[] encode = serialize(transporter);
            outputStream.write(encode);
            outputStream.flush();

            //等待server返回结果
            while(true){
                if(socket.getInputStream()!=null) {
                    byte[] decode = new byte[1024];
                    inputStream = socket.getInputStream();
                    inputStream.read(decode);
                    transporter = deserialize(decode, Transporter.class);
                    System.out.println(transporter.getReturnValue());
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return transporter;
    }

    @Override
    public void send(Transporter transporter) {

    }

    @Override
    public Transporter request(Transporter transporter) {
        this.transporter=transporter;
        return transfer();
    }

    @Override
    public void run() {

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
}
