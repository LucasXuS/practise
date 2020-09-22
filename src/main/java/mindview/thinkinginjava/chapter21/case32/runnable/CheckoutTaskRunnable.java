package mindview.thinkinginjava.chapter21.case32.runnable;

import mindview.thinkinginjava.chapter21.case32.collection.Pool;

import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class CheckoutTaskRunnable<T> implements Runnable {
    private Pool<T> pool;
    private static int counter = 0;
    private final int id = counter++;

    public CheckoutTaskRunnable(Pool<T> p) {
        pool = p;
    }

    @Override
    public void run() {
        try {
            T item = pool.checkOut();
            System.out.println(item + " checked out!");
            TimeUnit.SECONDS.sleep(1);
            System.out.println(item + " is checking in!");
            pool.checkIn(item);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "CheckoutTaskRunnable id: " + id + " ";
    }
}
