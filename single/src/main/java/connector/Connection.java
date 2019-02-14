package connector;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import invoker.Invocation;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public interface Connection extends Runnable{

    void send(Transporter transporter);

    Transporter request(Transporter transporter);

    default <T> byte[] serialize(T transporter){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Hessian2Output hessianOutput = new Hessian2Output(bos);
        try {
            hessianOutput.writeObject(transporter);
            hessianOutput.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bos.toByteArray();
    };

    default <T> T deserialize(byte[] bytes,Class<T> clazz){
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        Hessian2Input hessian2Input = new Hessian2Input(bis);
        Object object = null;
        try {
            object = hessian2Input.readObject(clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (T)object;
    };



}
