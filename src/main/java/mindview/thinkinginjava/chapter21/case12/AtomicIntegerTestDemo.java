package mindview.thinkinginjava.chapter21.case12;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class AtomicIntegerTestDemo implements Runnable {

    private AtomicInteger i = new AtomicInteger(0);

    private void evenIncrement() {
        i.addAndGet(2);
    }

    public int getValue() {
        return i.get();
    }

    @Override
    public void run() {
        while (true)
            evenIncrement();
    }


    public static void main(String[] args) {
        AtomicIntegerTestDemo a = new AtomicIntegerTestDemo();
        ExecutorService e = Executors.newCachedThreadPool();
        e.execute(a);
        while (true) {
            int val = a.getValue();
            if (val % 2 != 0) {
                System.out.println(val);
                System.exit(0);
            }
        }
    }
}
