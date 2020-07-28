package mindview.thinkinginjava.chapter21.case03;

import mindview.thinkinginjava.chapter21.case01.runnable.LiftOff;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-07-28
 * @description: 线程休眠
 */
public class SleepingTask extends LiftOff {
    @Override
    public void run() {
        try {
            while (countDown-- > 0){
                System.out.println(status());
                // 休眠时，线程调度器优先选择其他线程执行，但是对于对调度线程特别精确的代码，不可使用
                // 但是输出的“均匀分布”确实因此产生
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new SleepingTask());
        }
        executorService.shutdown();
    }
}
