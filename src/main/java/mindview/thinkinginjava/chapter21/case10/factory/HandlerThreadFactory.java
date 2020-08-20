package mindview.thinkinginjava.chapter21.case10.factory;

import mindview.thinkinginjava.chapter21.case10.handler.MyUncaughtExceptionHandler;

import java.util.concurrent.ThreadFactory;


/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-08-20
 * @description: 修改工厂方法，在我们的方法里面为新线程增加handler
 */
public class HandlerThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        System.out.println(this + " creating new Thread");
        Thread t = new Thread(r);
        System.out.println("created " + t);
        t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        System.out.println("exception handler is : " + t.getUncaughtExceptionHandler());
        return t;
    }
}
