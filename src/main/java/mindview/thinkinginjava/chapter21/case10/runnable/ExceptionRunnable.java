package mindview.thinkinginjava.chapter21.case10.runnable;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-08-20
 * @description: 在线程内抛出一个异常
 */
public class ExceptionRunnable implements Runnable {
    @Override
    public void run() {
        throw new RuntimeException();
    }
}
