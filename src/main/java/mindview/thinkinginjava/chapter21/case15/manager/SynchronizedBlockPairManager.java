package mindview.thinkinginjava.chapter21.case15.manager;

import mindview.thinkinginjava.chapter21.case15.model.Pair;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class SynchronizedBlockPairManager extends PairManager {
    @Override
    public synchronized void increment() {
        Pair temp;
        synchronized (this) {
            pair.incrementX();
            pair.incrementY();
            temp = getPair();
        }
        store(temp);
    }
}
