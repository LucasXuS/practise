package mindview.thinkinginjava.chapter21.case20.runnable;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class BlockMutexRunnable implements Runnable {
    private BlockMutex blockMutex = new BlockMutex();

    private class BlockMutex {
        private Lock lock = new ReentrantLock();

        public BlockMutex() {
            lock.lock();
        }

        public void f() {
            try {
                lock.lockInterruptibly();
                System.out.println("lock acquired in f()");
            } catch (InterruptedException e) {
                System.out.println("interrupt from lock acquisition in f()");
            }
        }
    }

    @Override
    public void run() {
        System.out.println("Waiting for f() in BlockMutex");
        blockMutex.f();
        System.out.println("Broken out of block call");
    }
}
