package mindview.thinkinginjava.chapter21.case10.factory;

import mindview.thinkinginjava.chapter21.case10.handler.MyUncaughtExceptionHandler;

import java.util.concurrent.ThreadFactory;

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
