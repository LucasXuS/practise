package mindview.thinkinginjava.chapter21.case01;

import mindview.thinkinginjava.chapter21.case01.runnable.LiftOffRunnable;
import mindview.thinkinginjava.chapter21.case01.runnable.LiftOffRunnerRunnable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class TestBlockingQueuesDemo {
    private static void getKey() {
        try {
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void getKey(String message) {
        System.out.println(message);
        getKey();
    }

    private static void test(String message, BlockingQueue<LiftOffRunnable> queue) {
        System.out.println(message);
        LiftOffRunnerRunnable runnerRunnable = new LiftOffRunnerRunnable(queue);
        Thread t = new Thread(runnerRunnable);
        t.start();
        for (int i = 0; i < 5; i++) {
            runnerRunnable.add(new LiftOffRunnable(5));
        }
        getKey("Press 'Enter' (" + message + ")");
        t.interrupt();
        System.out.println("Finished " + message + " test");
    }

    public static void main(String[] args) {
        test("LinkedBlockingQueue", new LinkedBlockingQueue<>());
        test("ArrayBlockingQueue", new ArrayBlockingQueue<>(3));
        test("SynchronousQueue", new SynchronousQueue<>());
    }
}
