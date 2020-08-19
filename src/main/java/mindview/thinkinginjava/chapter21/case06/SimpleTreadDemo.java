package mindview.thinkinginjava.chapter21.case06;


import mindview.thinkinginjava.chapter21.case06.thread.SimpleThread;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-08-01
 * @description: 示例1：case06 和 case07的目的只有一个，就是隐藏runnable
 * 在非常简单的情况下，可以直接从Thread继承来代替runnable赋值的方式
 */
public class SimpleTreadDemo {

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new SimpleThread();
        }
    }
}
