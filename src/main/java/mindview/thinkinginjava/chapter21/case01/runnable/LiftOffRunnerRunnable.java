package mindview.thinkinginjava.chapter21.case01.runnable;

import java.util.concurrent.BlockingQueue;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-09-14
 * @description: 将LiftOff串行输出
 */

public class LiftOffRunnerRunnable implements Runnable {

    private BlockingQueue<LiftOffRunnable> rockets;

    public LiftOffRunnerRunnable(BlockingQueue<LiftOffRunnable> queue) {
        this.rockets = queue;
    }

    public void add(LiftOffRunnable liftOffRunnable) {
        try {
            rockets.put(liftOffRunnable);
        } catch (InterruptedException e) {
            System.out.println("interrupted during put()");
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                LiftOffRunnable rocket = rockets.take();
                rocket.run();
            }
        }catch (InterruptedException e){
            System.out.println("waking from take()");
        }
        System.out.println("Exiting LiftOffRunner");
    }
}
