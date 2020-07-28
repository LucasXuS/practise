package mindview.thinkinginjava.chapter21.case04;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-07-28
 * @description: 设置优先级。优先级和sleep一样，只要在本线程声明就好，但是我们通过包装runnable时添加参数
 * 可以在主线程定义每个线程的优先级。
 */
public class SimpleProperties implements Runnable {

    private int countDown = 5;
    // 没有实际意义，只是为了模拟一个占用时间的语句
    private volatile double d;
    // 包装runnable，在初始化的时候用优先级作为参数进行初始化
    private int priority;

    public SimpleProperties(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return Thread.currentThread() + ": " + countDown;
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority(this.priority);
        while (true) {
            for (int i = 1; i < 100000; i++) {
                d += (Math.PI + Math.E) / (double) i;
                if (i % 1000 == 0) {
                    // yield()为让步，可以宣告线程的暂时休息，但是精确的线程转换不可依赖滥用
                    Thread.yield();
                }
            }
            System.out.println(this);
            if (--countDown == 0) {
                return;
            }
        }
    }

    public static void main(String[] agrs) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 优先级多种多样，一般根据搭载虚拟机的系统决定，但是真正好用的就三个，min max norm
        for (int i = 0; i < 5; i++) {
            executorService.execute(new SimpleProperties(Thread.MIN_PRIORITY));
        }
        executorService.execute(new SimpleProperties(Thread.MAX_PRIORITY));
        executorService.shutdown();

    }
}
