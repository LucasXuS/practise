package mindview.thinkinginjava.chapter21.case30;

import mindview.thinkinginjava.chapter21.case30.runnable.PrioritizedTaskConsumerRunnable;
import mindview.thinkinginjava.chapter21.case30.runnable.PrioritizedTaskProducerRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class PriorityBlockingQueueDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        PriorityBlockingQueue<Runnable> queue = new PriorityBlockingQueue<>();
        executorService.execute(new PrioritizedTaskProducerRunnable(queue, executorService));
        executorService.execute(new PrioritizedTaskConsumerRunnable(queue));
    }
}
