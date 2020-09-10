package mindview.thinkinginjava.chapter21.case20;

import mindview.thinkinginjava.chapter21.case20.runnable.BlockMutexRunnable;

import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class BlockMutexDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new BlockMutexRunnable());
        t.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("issuing t.interrupt");
        t.interrupt();
    }
}
