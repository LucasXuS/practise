package mindview.thinkinginjava.chapter21.case18;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class EntranceDemo {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i = 0; i < 5; i++){
            executorService.execute(new EntranceRunnable(i));
        }
        TimeUnit.SECONDS.sleep(5);
        EntranceRunnable.cancel();
        executorService.shutdown();
        if(!executorService.awaitTermination(250, TimeUnit.MILLISECONDS)){
            System.out.println("some of the tasks has not been killed!");
        }
        System.out.println("Total: " + EntranceRunnable.getTotalCount());
        System.out.println("Sum of the Entrance: " + EntranceRunnable.sumEntrances());
    }
}
