package mindview.thinkinginjava.chapter21.case24.runnable;

import mindview.thinkinginjava.chapter21.case24.collection.ToastQueue;
import mindview.thinkinginjava.chapter21.case24.model.Toast;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class EaterRunnable implements Runnable {
    private ToastQueue finishQueue;
    private int count = 0;

    public EaterRunnable(ToastQueue finishQueue) {
        this.finishQueue = finishQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast t = finishQueue.take();
                if (t.getId() != count++ || t.getStatus() != Toast.Status.JAMMED) {
                    System.out.println(">>>>Error: " + t);
                    System.exit(1);
                } else {
                    System.out.println("Chomp! " + t);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Eater interrupted!");
        }
        System.out.println("Eater off!");
    }
}
