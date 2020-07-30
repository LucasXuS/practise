package mindview.thinkinginjava.chapter21.case05.runnable;

import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-07-29
 * @description: 需要一个包含finally代码块的runnable
 */
public class ADaemon implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println("Starting ADaemon!");
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            System.out.println("exception");
        } finally {
            System.out.println("finally");
        }
    }
}
