package mindview.thinkinginjava.chapter21.case29;

import mindview.thinkinginjava.chapter21.case29.runnable.DelayedTaskConsumerRunnable;
import mindview.thinkinginjava.chapter21.case29.runnable.DelayedTaskRunnable;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class DelayQueueDemo {


    public static void main(String[] args) {
        Random random = new Random(47);
        ExecutorService executorService = Executors.newCachedThreadPool();
        DelayQueue<DelayedTaskRunnable> queue =
                new DelayQueue<>();
        for (int i = 0; i < 20; i++) {
            queue.put(new DelayedTaskRunnable(random.nextInt(5000)));
        }
        queue.add(new DelayedTaskRunnable.EndSentinelRunnable(5000, executorService));
        executorService.execute(new DelayedTaskConsumerRunnable(queue));
    }
}
