package mindview.thinkinginjava.chapter21.case30.runnable;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class PrioritizedTaskProducerRunnable implements Runnable {
    private Random random = new Random(47);
    private Queue<Runnable> queue;
    private ExecutorService executorService;

    public PrioritizedTaskProducerRunnable(Queue<Runnable> q, ExecutorService e) {
        this.queue = q;
        this.executorService = e;
    }


    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            queue.add(new PrioritizedTaskRunnable(random.nextInt(10)));
            //System.out.println(queue);
            Thread.yield();
        }

        try {
            for (int i = 0; i < 10; i++) {
                TimeUnit.MILLISECONDS.sleep(250);
                queue.add(new PrioritizedTaskRunnable(10));

            }
            for (int i = 1; i < 10; i++) {
                queue.add(new PrioritizedTaskRunnable(i));
            }
            queue.add(new PrioritizedTaskRunnable.EndSentinelRunnable(executorService));
        } catch (InterruptedException e) {

        }
        System.out.println("Finished PrioritizedTaskProducerRunnable");
    }
}
