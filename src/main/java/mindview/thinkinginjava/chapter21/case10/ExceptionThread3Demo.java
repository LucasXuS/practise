package mindview.thinkinginjava.chapter21.case10;

import mindview.thinkinginjava.chapter21.case10.handler.MyUncaughtExceptionHandler;
import mindview.thinkinginjava.chapter21.case10.runnable.ExceptionRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-08-20
 * @description: 可以直接为Thread类设置默认的exception handler可以达到同样的效果，但是不如factory这么灵活
 *
 * 线程组优先回去找有没有专属的exception handler 如果没有，就去使用默认的
 */
public class ExceptionThread3Demo {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new ExceptionRunnable());
    }
}
