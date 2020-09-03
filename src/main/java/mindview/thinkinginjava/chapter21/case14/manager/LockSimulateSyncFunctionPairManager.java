package mindview.thinkinginjava.chapter21.case14.manager;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class LockSimulateSyncFunctionPairManager extends PairManager {
    Lock lock = new ReentrantLock();
    @Override
    public synchronized void increment() {
        lock.lock();
        try {
            pair.incrementX();
            pair.incrementY();
            store(getPair());
        }finally {
            lock.unlock();
        }

    }
}
