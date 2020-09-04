package mindview.thinkinginjava.chapter21.case15.manager;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-09-04
 * @description: 利用lock对象模仿SynchronizedFunctionPairManager,因为同步块语句较多，运行效率低于LockSimulateSyncBlockPairManager
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
