package mindview.thinkinginjava.chapter21.case11.runnable;

import mindview.thinkinginjava.chapter21.case11.generator.IntGenerator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class EvenChecker implements Runnable {
    private IntGenerator generator;
    private final int id;

    public EvenChecker(IntGenerator g, int ident) {
        generator = g;
        id = ident;
    }

    @Override
    public void run() {
        while (!generator.isCanceled()) {
            int val = generator.next();
            if (val % 2 != 0) {
                System.out.println(val + " is not even!");
                generator.cancel();
            }
        }
    }

    public static void test(IntGenerator g, int count) {
        System.out.println("press ctrl-c to exit");
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++) {
            executorService.execute(new EvenChecker(g, i));
        }
    }

    public static void test(IntGenerator g) {
        test(g, 50);
    }
}
