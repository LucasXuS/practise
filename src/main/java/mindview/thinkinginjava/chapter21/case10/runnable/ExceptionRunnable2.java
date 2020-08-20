package mindview.thinkinginjava.chapter21.case10.runnable;


/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-08-20
 * @description: 相比于示例1增加一个当前线程getUncaughtExceptionHandler的输出
 */
public class ExceptionRunnable2 implements Runnable {
    @Override
    public void run() {
        Thread t = Thread.currentThread();
        System.out.println("run() by " + t);
        System.out.println("exception handler is : " + t.getUncaughtExceptionHandler());
        throw new RuntimeException();
    }
}
