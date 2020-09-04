package mindview.thinkinginjava.chapter21.case11;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-09-04
 * @description: java对于Lock类提供了tryLock的操作，当在多线程中，我们某个线程程序运行的时候无法获得锁，可以先去做别的事情，可以获得锁的时候在执行
 * 需要线程安全的代码。这样可以提升程序的运行效率。
 */
public class AttemptingLockingDemo {
    private ReentrantLock lock = new ReentrantLock();

    public void timed() {
        boolean capture = false;
        try {
            capture = lock.tryLock(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("tryLock(2, TimeUnit.SECONDS):" + capture);
        } finally {
            if (capture)
                lock.unlock();
        }
    }

    public void untimed() {
        boolean capture = lock.tryLock();
        ;
        try {
            System.out.println("tryLock():" + capture);
        } finally {
            if (capture)
                lock.unlock();
        }
    }

    public static void main(String[] args) {
        AttemptingLockingDemo a = new AttemptingLockingDemo();
        a.untimed();
        a.timed();
        new Thread() {
            {
                setDaemon(false);
            }

            @Override
            public void run() {
                a.lock.lock();
                System.out.println("acquired");
            }
        }.start();
        Thread.yield();
        a.untimed();
        a.timed();
    }

}
