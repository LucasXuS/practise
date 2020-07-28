package mindview.thinkinginjava.chapter21.case01.thread;

import mindview.thinkinginjava.chapter21.case01.runnable.LiftOff;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-07-27
 * @description: 示例3，MainTread BasicTread的进一步示例，我们创建更多的线程。
 */
public class MoreBasicThreads {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new LiftOff()).start();
        }
        System.out.println("main thread start.");
    }
}
