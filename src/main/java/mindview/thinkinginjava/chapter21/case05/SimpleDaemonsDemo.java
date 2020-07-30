package mindview.thinkinginjava.chapter21.case05;

import java.util.concurrent.TimeUnit;


/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-07-28
 * @description: 示例1：后台（daemon）线程，当所有的非后台线程结束时，程序也就终止了，同时杀死所有后台线程
 */
public class SimpleDaemonsDemo implements Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread() + " " + this);
            } catch (InterruptedException e) {
                System.out.println("sleep() interrupted");
            }
        }
    }


    public static void main(String[] args) {
        // 创建10个后台线程，当后面的sleep在100毫秒时，只输出main course
        // 因为结束后，所有的线程都被杀死了
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new SimpleDaemonsDemo());
            // 此处我们可知我们可以直接通过thread对象定义线程为后台线程
            t.setDaemon(true);
            t.start();
        }
        System.out.println("main course");
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
