package mindview.thinkinginjava.chapter21.case01.executor;

import mindview.thinkinginjava.chapter21.case01.runnable.LiftOff;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-07-27
 * @description: 示例2， 和CachedThreadPool不同，我们强调FixedTreadPool是有界的。
 */
public class FixedThreadPool {
    // 有边界
    public static void main(String[] args) {
        // FixedThreadPool有两个特征：
        // 1 有界的，并不会因为盲目添加线程使得cpu崩掉
        // 2 一次性进行预先的线程分配（此处开销最大），但是在后续添加线程的时候（相比CachedThreadPool）开销更小
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executorService.execute(new LiftOff());
        }
        executorService.shutdown();
    }
}
