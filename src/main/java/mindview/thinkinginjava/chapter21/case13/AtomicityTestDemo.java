package mindview.thinkinginjava.chapter21.case13;

import mindview.thinkinginjava.chapter21.case13.runnable.AtomicIntegerTestRunnable;
import mindview.thinkinginjava.chapter21.case13.runnable.IntegerTestRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class AtomicityTestDemo {

    public static void main(String[] args) {
        //test(new NoAtomicIntegerTestRunnable());
        test(new AtomicIntegerTestRunnable());
    }

    private static void test(IntegerTestRunnable runnable) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(runnable);
        while (true) {
            int val = runnable.getValue();
            if (val % 2 != 0) {
                System.out.println(val);
                System.exit(0);
            }
        }
    }
}
