package processor;

import export.Exporter;
import export.URL;
import invoker.Invoker;

public interface Processor {

    Exporter export(Invoker invoker);

    <T> Invoker<T> refer(Class<?> clazz, URL url);

}
