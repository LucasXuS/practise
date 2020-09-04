package mindview.thinkinginjava.chapter21.case15.manager;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-09-04
 * @description: 使用同步函数，但是因为运行代码相对较多，运行效率高于SynchronizedBlockPairManager
 */
public class SynchronizedFunctionPairManager extends PairManager {
    @Override
    public synchronized void increment() {
        pair.incrementX();
        pair.incrementY();
        store(getPair());
    }
}
