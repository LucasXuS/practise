package mindview.thinkinginjava.chapter21.case01.thread;

import mindview.thinkinginjava.chapter21.case01.runnable.LiftOff;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-07-27
 * @description: 示例2，和MainTread相对应，前者示例单一线程的任务，此处创建另一个新线程
 */
public class BasicTreads {
    public static void main(String[] agrs) {
        // 最基本的方法，用Thread类创建一个新线程。
        Thread t = new Thread(new LiftOff());
        t.start();
        System.out.println("main thread start");
    }
}
