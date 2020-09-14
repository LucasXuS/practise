package mindview.thinkinginjava.chapter21.case24;

import mindview.thinkinginjava.chapter21.case24.collection.ToastQueue;
import mindview.thinkinginjava.chapter21.case24.runnable.ButtererRunnable;
import mindview.thinkinginjava.chapter21.case24.runnable.EaterRunnable;
import mindview.thinkinginjava.chapter21.case24.runnable.JammerRunnable;
import mindview.thinkinginjava.chapter21.case24.runnable.ToasterRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class ToastDemo {
    public static void main(String[] args) throws InterruptedException {
        ToastQueue dryQueue = new ToastQueue(),
                butteredQueue = new ToastQueue(),
                finishedQueue = new ToastQueue();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new ToasterRunnable(dryQueue));
        executorService.execute(new ButtererRunnable(dryQueue, butteredQueue));
        executorService.execute(new JammerRunnable(butteredQueue, finishedQueue));
        executorService.execute(new EaterRunnable(finishedQueue));
        TimeUnit.SECONDS.sleep(10);
        executorService.shutdownNow();
    }
}
