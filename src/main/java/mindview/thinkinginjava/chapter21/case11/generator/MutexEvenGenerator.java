package mindview.thinkinginjava.chapter21.case11.generator;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-09-04
 * @description: 这里是另一个办法，我们通过lock来解决争用的问题，实际上 synchronized 和 lock在写法上是可以互相转换的。
 */
public class MutexEvenGenerator extends IntGenerator {
    private Lock lock = new ReentrantLock();

    private int currentEvenValue = 0;

    @Override
    public int next() {
        lock.lock();
        try {
            // 这一步操作很危险
            currentEvenValue++;
            // 在这里运行此句可以增大失败的可能性
            Thread.yield();
            currentEvenValue++;
            return currentEvenValue;
        } finally {
            lock.unlock();
        }

    }
}
