package mindview.thinkinginjava.chapter21.case19;

import mindview.thinkinginjava.chapter21.case19.runnable.IOBlockRunnable;
import mindview.thinkinginjava.chapter21.case19.runnable.SleepBlockRunnable;
import mindview.thinkinginjava.chapter21.case19.runnable.SynchronizedBlockRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class InterruptedDemo {

    private static ExecutorService executorService
            = Executors.newCachedThreadPool();

    static void test(Runnable r) throws InterruptedException {
        Future<?> future = executorService.submit(r);
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("Interrupting " + r.getClass().getName());
        future.cancel(true);
        System.out.println("Interrupt sent to " + r.getClass().getName());
    }

    public static void main(String[] args) throws InterruptedException {
        test(new SleepBlockRunnable());
        test(new IOBlockRunnable(System.in));
        test(new SynchronizedBlockRunnable());
        System.out.println("Aborting with System.exit(0)");
        System.exit(0);
    }
}
