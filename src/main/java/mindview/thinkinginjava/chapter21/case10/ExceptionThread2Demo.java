package mindview.thinkinginjava.chapter21.case10;

import mindview.thinkinginjava.chapter21.case10.factory.HandlerThreadFactory;
import mindview.thinkinginjava.chapter21.case10.runnable.ExceptionRunnable2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExceptionThread2Demo {
    public static void main(String[] args) {
        // 通过factory 来给thread设置handler
        ExecutorService executorService
                = Executors.newCachedThreadPool(new HandlerThreadFactory());
        executorService.execute(new ExceptionRunnable2());
    }
}
