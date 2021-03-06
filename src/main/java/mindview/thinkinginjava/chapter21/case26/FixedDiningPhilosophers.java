package mindview.thinkinginjava.chapter21.case26;

import mindview.thinkinginjava.chapter21.case26.model.Chopstick;
import mindview.thinkinginjava.chapter21.case26.runnable.PhilosopherRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class FixedDiningPhilosophers {
    public static void main(String[] args) throws InterruptedException {
        int ponder = 0;
        int size = 5;
        Chopstick[] chopsticks = new Chopstick[size];
        for (int i = 0; i < size; i++) {
            chopsticks[i] = new Chopstick();
        }

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < size - 1; i++) {
            executorService.execute(
                    new PhilosopherRunnable(chopsticks[i], chopsticks[i + 1], i, ponder));
        }
        executorService.execute(
                new PhilosopherRunnable(chopsticks[0], chopsticks[size - 1], size - 1, ponder));
        TimeUnit.SECONDS.sleep(5);
        executorService.shutdownNow();
    }
}
