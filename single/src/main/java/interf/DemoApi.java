package interf;

import annonation.NonRemote;
import annonation.Service;

public interface DemoApi {

    void hello();

    String hello1(String msg);

    @NonRemote
    void notexecute();

}
