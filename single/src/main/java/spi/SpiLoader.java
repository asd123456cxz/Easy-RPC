package spi;

import Exception.RpcException;

@SuppressWarnings("unchecked")
public class SpiLoader<T> {

    private Class<T> loader;

    public SpiLoader(Class classtype){
        loader=classtype;
    }

    public T adaptiveLoadExtension(){
        return null;
    }

    public T loadExtension(String... names){
        T target=null;
        try {
            for(String name:names){
                Class<?> clazz = ClassLoader.getSystemClassLoader().loadClass(name);
                if(loader.isAssignableFrom(clazz)){
                    target=(T) clazz.newInstance();
                }else{
                    throw new RpcException("extension not match");
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return target;
    }

    private void WrapLoader(Class<T> clazz){

    }

}
