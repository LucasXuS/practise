package mindview.thinkinginjava.chapter21.case24;



import mindview.thinkinginjava.chapter21.case24.runnable.WaxOnRunnable;
import mindview.thinkinginjava.chapter21.case24.model.Car;
import mindview.thinkinginjava.chapter21.case24.runnable.WaxOffRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class WaxDemo {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Car car = new Car();
        executorService.execute(new WaxOffRunnable(car));
        executorService.execute(new WaxOnRunnable(car));
        TimeUnit.SECONDS.sleep(5);
        executorService.shutdownNow();
    }
}
