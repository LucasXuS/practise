package mindview.thinkinginjava.chapter21.case27;

import mindview.thinkinginjava.chapter21.case27.runnable.TaskPortionRunnable;
import mindview.thinkinginjava.chapter21.case27.runnable.WaitingTaskRunnable;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class CountDownLatchDemo {
    static final int SIZE = 100;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(SIZE);
        for (int i = 0; i < 10; i++) {
            executorService.execute(new WaitingTaskRunnable(latch));
        }
        for (int i = 0; i < SIZE; i++) {
            executorService.execute(new TaskPortionRunnable(latch));
        }
        System.out.println("Launched all tasks");
        executorService.shutdown();
    }
}
