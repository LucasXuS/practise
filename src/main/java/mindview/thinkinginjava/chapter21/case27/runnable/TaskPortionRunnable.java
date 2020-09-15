package mindview.thinkinginjava.chapter21.case27.runnable;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class TaskPortionRunnable implements Runnable {

    private static int counter = 0;
    private final int id = counter++;
    private static Random random = new Random(47);
    private final CountDownLatch latch;

    public TaskPortionRunnable(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            doWork();
            latch.countDown();
        } catch (InterruptedException e) {

        }

    }

    public void doWork() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(random.nextInt(2000));
        System.out.println(this + " completed");
    }

    @Override
    public String toString() {
        return String.format("%1$-3d", id);
    }
}
