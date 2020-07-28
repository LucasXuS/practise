package mindview.thinkinginjava.chapter21.case01.executor;

import mindview.thinkinginjava.chapter21.case01.runnable.LiftOff;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-07-27
 * @description: 示例3， FixedTreadPool的特殊情况，仅仅只分配一个额外线程（相比于主线程）
 */
public class SingleThreadExecutor {
    public static void main(String[] args) {
        // 由于使用的SingleThreadExecutor,只有一个额外线程，因此，runnable不会穿插执行而是顺序执行。
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new LiftOff());
        }
        executorService.shutdown();
    }
}
