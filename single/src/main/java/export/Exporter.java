package export;

import invoker.Invoker;

public class Exporter <T> {

    private Invoker<T> invoker;

    public Exporter(Invoker<T> invoker) {
        this.invoker = invoker;
    }

    public Invoker<T> getInvoker() {
        return invoker;
    }

    public void setInvoker(Invoker<T> invoker) {
        this.invoker = invoker;
    }
}
