package mindview.thinkinginjava.chapter21.case30.runnable;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class PrioritizedTaskConsumerRunnable implements Runnable {

    private PriorityBlockingQueue<Runnable> queue;

    public PrioritizedTaskConsumerRunnable(PriorityBlockingQueue<Runnable> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                queue.take().run();
                //System.out.println(queue);
            }
        } catch (InterruptedException e) {
        }
        System.out.println("Finished PrioritizedTaskConsumerRunnable");
    }
}
