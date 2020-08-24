package mindview.thinkinginjava.chapter21.case12;


import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class VolatileTestDemo {
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
