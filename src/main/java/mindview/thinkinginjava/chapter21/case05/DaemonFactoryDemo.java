package mindview.thinkinginjava.chapter21.case05;

import mindview.thinkinginjava.chapter21.case05.factory.DaemonThreadFactory;
import mindview.thinkinginjava.chapter21.case05.runnable.DaemonFactoryRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-07-29
 * @description: 示例2：通过工厂来创建后台线程
 */
public class DaemonFactoryDemo {
    public static void main(String[] agrs) {
        // Executor 可以指定ThreadFactory派生类！
        // 在这里的代码可以说明，Executor的内核是依靠ThreadFactory生产Thread实现的。
        ExecutorService exec = Executors.newCachedThreadPool(new DaemonThreadFactory());
        for (int i = 0; i < 10; i++) {
            exec.execute(new DaemonFactoryRunnable());
        }
        System.out.println("all daemons started!");
        try {
            TimeUnit.MILLISECONDS.sleep(500);// run for a while
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
