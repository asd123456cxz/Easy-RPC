package processor;

import connector.ServiceConnection;
import export.LocalExporter;
import invoker.Invocation;
import invoker.Invoker;
import io.netty.channel.Channel;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


/*
    最底层服务域
 */
public class CommonProcessor implements Processor{

    private ServiceConnection connection;

    private List<Invoker> invokers = new ArrayList<>();


    @Override
    public void export(Invoker invoker) {
        Invocation invocation = buildInvocation();
        try {
            invoker.invoke(invocation);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        doexport();
        invokers.add(invoker);
    }


    @Override
    public void refer() {

    }

    public Object reply(Channel channel){

        return null;
    }


    private void doexport(){


        //开启socket
        connection.openServer();
    }


    public Invocation buildInvocation(){
        return new Invocation();
    }


}
