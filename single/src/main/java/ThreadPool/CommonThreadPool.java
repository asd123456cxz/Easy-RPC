package ThreadPool;

import javax.naming.InitialContext;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("unchecked")
public class CommonThreadPool {

    private static int CPU= Runtime.getRuntime().availableProcessors();

    public static ThreadPoolExecutor getDefault(){
        return inner.pool;
    }

    private static class inner{
        private static ThreadPoolExecutor pool = init();
        private static ThreadPoolExecutor init(){
            //默认策略
            pool = new ThreadPoolExecutor(CPU * 2,
                    Integer.MAX_VALUE,
                    60,
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue(10)
            );
            return pool;
        }
    }


}
