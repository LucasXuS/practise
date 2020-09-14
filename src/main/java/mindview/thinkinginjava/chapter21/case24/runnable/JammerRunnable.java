package mindview.thinkinginjava.chapter21.case24.runnable;

import mindview.thinkinginjava.chapter21.case24.collection.ToastQueue;
import mindview.thinkinginjava.chapter21.case24.model.Toast;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class JammerRunnable implements Runnable {
    private ToastQueue finishedQueue, butteredQueue;

    public JammerRunnable(ToastQueue butteredQueue, ToastQueue finishedQueue) {
        this.butteredQueue = butteredQueue;
        this.finishedQueue = finishedQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast t = butteredQueue.take();
                t.jam();
                System.out.println(t);
                finishedQueue.add(t);
            }
        } catch (InterruptedException e) {
            System.out.println("Jammer interrupted!");
        }
        System.out.println("Jammer off!");

    }
}
