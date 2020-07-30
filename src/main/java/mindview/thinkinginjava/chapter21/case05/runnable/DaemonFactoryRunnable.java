package mindview.thinkinginjava.chapter21.case05.runnable;

import java.util.concurrent.TimeUnit;

public class DaemonFactoryRunnable implements Runnable {
    @Override
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(100);
            System.out.println(Thread.currentThread() + " " + this);
        } catch (InterruptedException e) {
            System.out.println("interrupted");
        }
    }
}
