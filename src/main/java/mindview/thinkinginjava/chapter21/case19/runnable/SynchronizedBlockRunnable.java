package mindview.thinkinginjava.chapter21.case19.runnable;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class SynchronizedBlockRunnable implements Runnable {
    public synchronized void f() {
        while (true)
            Thread.yield();
    }

    public SynchronizedBlockRunnable() {
        new Thread(this::f).start();
    }

    @Override
    public void run() {
        System.out.println("trying to call f()");
        f();
        System.out.println("Exiting SynchronizedBlockRunnable.run()");
    }
}
