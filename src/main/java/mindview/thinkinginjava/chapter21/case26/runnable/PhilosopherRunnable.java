package mindview.thinkinginjava.chapter21.case26.runnable;

import mindview.thinkinginjava.chapter21.case26.model.Chopstick;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class PhilosopherRunnable implements Runnable {

    private Chopstick left, right;
    private final int id;
    private final int ponderFactor;
    private Random random = new Random(47);

    private void pause() throws InterruptedException {
        if (ponderFactor == 0) return;
        TimeUnit.MILLISECONDS.sleep(random.nextInt(ponderFactor * 250));
    }

    public PhilosopherRunnable(Chopstick left, Chopstick right, int ident, int ponder) {
        this.left = left;
        this.right = right;
        this.id = ident;
        this.ponderFactor = ponder;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println(this + " thinking");
                pause();
                System.out.println(this + " grabbing right");
                right.take();
                System.out.println(this + " grabbing left");
                left.take();
                System.out.println(this + " eating");
                pause();
                System.out.println(this + " dropping right");
                right.drop();
                System.out.println(this + " dropping left");
                left.drop();
            }
        } catch (InterruptedException e) {
            System.out.println(this +  " exiting via interrupt");
        }
    }
}
