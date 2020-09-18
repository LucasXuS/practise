package mindview.thinkinginjava.chapter21.case29.runnable;

import java.util.concurrent.DelayQueue;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class DelayedTaskConsumerRunnable implements Runnable{

    private DelayQueue<DelayedTaskRunnable> q;

    public DelayedTaskConsumerRunnable(DelayQueue<DelayedTaskRunnable> q){
        this.q = q;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                q.take().run();
            }
        }catch (InterruptedException e){

        }
        System.out.println("Finished DelayedTaskConsumer");
    }
}
