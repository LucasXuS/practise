package mindview.thinkinginjava.chapter21.case19.runnable;

import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class SleepBlockRunnable implements Runnable {


    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException from Sleep");
        }
        System.out.println("Exiting from SleepBlockRunnable.run()");
    }
}
