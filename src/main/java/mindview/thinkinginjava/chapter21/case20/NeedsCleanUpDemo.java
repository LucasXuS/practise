package mindview.thinkinginjava.chapter21.case20;

import mindview.thinkinginjava.chapter21.case20.runnable.NeedsCleanUpRunnable;

import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class NeedsCleanUpDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new NeedsCleanUpRunnable());
        t.start();
        TimeUnit.SECONDS.sleep(5);
        t.interrupt();
    }
}
