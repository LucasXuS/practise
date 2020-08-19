package mindview.thinkinginjava.chapter21.case10;

import mindview.thinkinginjava.chapter21.case10.handler.MyUncaughtExceptionHandler;
import mindview.thinkinginjava.chapter21.case10.runnable.ExceptionRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExceptionThread3Demo {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new ExceptionRunnable());
    }
}
