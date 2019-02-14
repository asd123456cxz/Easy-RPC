package demo;

import annonation.Service;
import interf.DemoApi;

@Service("DemoService")
public class DemoApiImpl implements DemoApi {

    @Override
    public void hello() {
        System.out.println("hello");
    }

    @Override
    public void notexecute() {
        System.out.println("not do");
    }

}
