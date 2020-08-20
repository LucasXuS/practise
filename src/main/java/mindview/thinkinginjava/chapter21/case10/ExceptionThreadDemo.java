package mindview.thinkinginjava.chapter21.case10;

import mindview.thinkinginjava.chapter21.case10.runnable.ExceptionRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-08-20
 * @description: 在分线程里抛出的exception 主线程是无法通过try-catch捕获到的
 */
public class ExceptionThreadDemo {
    public static void main(String[] args) {
        try {
            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.execute(new ExceptionRunnable());
        } catch (Exception e) {
            System.out.println("Exception has been handled");
        }
    }
}
