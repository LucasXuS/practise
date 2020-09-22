package mindview.thinkinginjava.chapter21.case32;

import mindview.thinkinginjava.chapter21.case32.collection.Pool;
import mindview.thinkinginjava.chapter21.case32.model.Fat;
import mindview.thinkinginjava.chapter21.case32.runnable.CheckoutTaskRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class SemaphoreDemo {

    public final static int SIZE = 25;

    public static void main(String[] args) throws InterruptedException {
        Pool<Fat> pool = new Pool<>(Fat.class, SIZE);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < SIZE; i++) {
            executorService.execute(new CheckoutTaskRunnable<>(pool));
        }
        System.out.println("all runnable is loaded");
        TimeUnit.SECONDS.sleep(2);
        List<Fat> list = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            Fat fat = pool.checkOut();
            System.out.println(i + " main() thread checked out");
            fat.operation();
            list.add(fat);
        }
        Future<?> future = executorService.submit(() -> {
            try {
                pool.checkOut();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        TimeUnit.SECONDS.sleep(2);
        future.cancel(true);
        System.out.println("checking in objects in " + list);
        for (Fat fat : list) {
            pool.checkIn(fat);
        }
        for (Fat fat : list) {
            pool.checkIn(fat);
        }

    }
}
