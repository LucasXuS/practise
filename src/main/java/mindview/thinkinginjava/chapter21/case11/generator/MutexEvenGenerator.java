package mindview.thinkinginjava.chapter21.case11.generator;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
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
