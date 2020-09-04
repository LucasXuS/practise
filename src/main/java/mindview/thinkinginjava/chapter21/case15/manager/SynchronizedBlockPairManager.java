package mindview.thinkinginjava.chapter21.case15.manager;

import mindview.thinkinginjava.chapter21.case15.model.Pair;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-09-04
 * @description: 使用同步代码块，但是因为运行代码相对较少，运行效率高于SynchronizedFunctionPairManager
 */
public class SynchronizedBlockPairManager extends PairManager {
    @Override
    public void increment() {
        Pair temp;
        synchronized (this) {
            pair.incrementX();
            pair.incrementY();
            temp = getPair();
        }
        store(temp);
    }
}
