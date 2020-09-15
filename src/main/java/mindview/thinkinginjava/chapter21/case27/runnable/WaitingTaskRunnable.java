package mindview.thinkinginjava.chapter21.case27.runnable;

import java.util.concurrent.CountDownLatch;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class WaitingTaskRunnable implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private final CountDownLatch latch;

    public WaitingTaskRunnable(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            latch.await();
            System.out.println("Latch barrier passed for " + this);
        } catch (InterruptedException e) {
            System.out.println(this + " interrupted");
        }

    }

    @Override
    public String toString() {
        return String.format("WaitingTask %1$-3d", id);
    }
}
