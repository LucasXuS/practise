package mindview.thinkinginjava.chapter21.case07;


import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-08-01
 * @description: 示例1：为了实现SimpleThreadDemo中所提到的因为继承了Thread而不灵活的问题
 * 我们可以使用内部类去解决这个问题
 */
public class InnerThread1 {
    private int countDown = 5;

    // 我们可以让内部类继承 Thread
    private class Inner extends Thread {
        Inner(String name) {
            super(name);
            start();
        }

        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println(this);
                    if (--countDown == 0) {
                        return;
                    }
                    TimeUnit.MILLISECONDS.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public String toString() {
            return getName() + ":(" + countDown + ")";
        }
    }

    Inner inner;

    public InnerThread1(String name) {
        inner = new Inner(name);
    }
}
