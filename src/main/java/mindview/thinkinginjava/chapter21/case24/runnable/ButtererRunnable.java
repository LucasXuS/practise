package mindview.thinkinginjava.chapter21.case24.runnable;

import mindview.thinkinginjava.chapter21.case24.collection.ToastQueue;
import mindview.thinkinginjava.chapter21.case24.model.Toast;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class ButtererRunnable implements Runnable{
    private ToastQueue dryQueue, butteredQueue;

    public ButtererRunnable(ToastQueue dryQueue, ToastQueue butteredQueue){
        this.dryQueue = dryQueue;
        this.butteredQueue = butteredQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                Toast t = dryQueue.take();
                t.butter();
                System.out.println(t);
                butteredQueue.add(t);
            }
        } catch (InterruptedException e) {
            System.out.println("Butterer interrupted!");
        }
        System.out.println("Butterer off!");

    }
}
