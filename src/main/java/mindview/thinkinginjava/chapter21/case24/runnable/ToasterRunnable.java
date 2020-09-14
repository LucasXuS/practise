package mindview.thinkinginjava.chapter21.case24.runnable;

import mindview.thinkinginjava.chapter21.case24.collection.ToastQueue;
import mindview.thinkinginjava.chapter21.case24.model.Toast;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class ToasterRunnable implements Runnable {
    private ToastQueue toastQueue;
    private int count = 0;
    private Random random = new Random(47);

    public ToasterRunnable(ToastQueue tq) {
        this.toastQueue = tq;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(100 + random.nextInt(500));
                Toast t = new Toast(count++);
                System.out.println(t);
                toastQueue.put(t);
            }
        } catch (InterruptedException e) {
            System.out.println("Toaster interrupted!");
        }
        System.out.println("Toaster off!");
    }
}
