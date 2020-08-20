package mindview.thinkinginjava.chapter21.case09;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-08-20
 * @description: 按下按钮所触发的新线程，在这个线程里面，执行ui按钮的功能。
 */
public class ResponsiveUIThread extends Thread {

    public ResponsiveUIThread() {
        setDaemon(false);
        start();
    }

    @Override
    public void run() {
        // 占用时间的操作
        double d = 10000d;
        while (d > 0) {
            d--;
        }
        // 这个功能运行完成以后就给出反馈
        System.out.println("function finished");
    }
}
