package mindview.thinkinginjava.chapter21.case01.thread;

import mindview.thinkinginjava.chapter21.case01.runnable.LiftOffRunnable;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-07-27
 * @description: 示例1，单独运行runnable并不是多线程代码, run中的代码在主线程中运行。
 */
public class MainThread {
    public static void main(String[] args) {
        // 并不是多线程代码, run中的代码在主线程中运行。
        LiftOffRunnable liftOff = new LiftOffRunnable();
        liftOff.run();
        System.out.println("main thread");
    }
}
