package mindview.thinkinginjava.chapter21.case01.executor;

import mindview.thinkinginjava.chapter21.case01.runnable.LiftOffRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-07-27
 * @description: 示例1，Executor和Thread一样，是任务（runnable）和主线程的中间层，CachedThreadPool是最常用的线程池。
 * 在这里首先重现MoreBasicTreads里面的效果。CachedThreadPool是最常用的线程池。
 */
public class CachedThreadPool {

    // 基本可以满足增加线程的需求，而且会把使用过的线程缓存起来，再次创建新线程时，会优先调用已经创建的线程
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new LiftOffRunnable());
        }
        // 防止新任务提交给Executor
        executorService.shutdown();
    }
}
