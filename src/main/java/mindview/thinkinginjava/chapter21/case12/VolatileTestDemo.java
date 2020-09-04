package mindview.thinkinginjava.chapter21.case12;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-09-04
 * @description: 这是一个从第三方网站摘抄的例子，用来说明volatile的重要性
 */
import java.util.concurrent.TimeUnit;

public class VolatileTestDemo {

    // 这里如果给stop标识volatile，此值在主线程改过以后，在分线程内会被感知到，程序会结束，
    // 但是，如不给stop标识volatile 那么分线程不会感知到stop变化，那么分线程将一直运行。
    //private static boolean stop = false;
    private static volatile boolean stop = false;

    public static void main(String[] args) throws Exception {
        Thread t = new Thread(() -> {
            int i = 0;
            while (!stop) {
                i++;
            }
        });
        t.start();
        Thread.sleep(1000);
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Stop Thread");
        stop = true;
    }
}
