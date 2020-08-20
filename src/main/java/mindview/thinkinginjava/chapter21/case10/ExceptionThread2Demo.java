package mindview.thinkinginjava.chapter21.case10;

import mindview.thinkinginjava.chapter21.case10.factory.HandlerThreadFactory;
import mindview.thinkinginjava.chapter21.case10.runnable.ExceptionRunnable2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-08-20
 * @description: 通过factory方法，我们起到了给thread添加exception handler，达到处理exception的目的
 */
public class ExceptionThread2Demo {
    public static void main(String[] args) {
        // 通过factory 来给thread设置handler
        ExecutorService executorService
                = Executors.newCachedThreadPool(new HandlerThreadFactory());
        executorService.execute(new ExceptionRunnable2());
    }
}
