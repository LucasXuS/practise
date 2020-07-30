package mindview.thinkinginjava.chapter21.case05;


import mindview.thinkinginjava.chapter21.case05.runnable.Daemon;

import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-07-29
 * @description: 示例3：后台线程的任务中，所创建的新线程，如不主动设置，将默认为后台线程
 */
public class DaemonsDemo {
    public static void main(String[] args) {
        Thread t = new Thread(new Daemon());
        t.setDaemon(true);
        t.start();
        System.out.println("t.isDaemon() = " + t.isDaemon());
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
