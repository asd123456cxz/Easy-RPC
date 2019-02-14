package interf;

import annonation.NonRemote;
import annonation.Service;

public interface DemoApi {

    void hello();

    @NonRemote
    void notexecute();

}
