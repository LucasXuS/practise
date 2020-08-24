package mindview.thinkinginjava.chapter21.case12;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class AtomicityTestDemo implements Runnable {
    private int i = 0;

    // public synchronized int getValue()
    public int getValue() {
        return i;
    }

    @Override
    public void run() {
        while (true) {
            evenIncrement();
        }
    }

    private synchronized void evenIncrement() {
        i++;
        i++;
    }

    public static void main(String[] args) {
        AtomicityTestDemo demo = new AtomicityTestDemo();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(demo);
        while (true) {
            int val = demo.getValue();
            if (val % 2 != 0) {
                System.out.println(val);
                System.exit(0);
            }
        }
    }
}
