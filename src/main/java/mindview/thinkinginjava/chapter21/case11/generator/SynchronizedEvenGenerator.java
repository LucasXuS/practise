package mindview.thinkinginjava.chapter21.case11.generator;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-09-04
 * @description: 我们通过synchronized函数来解决争用的问题
 */
public class SynchronizedEvenGenerator extends IntGenerator{
    private int currentEvenValue= 0;
    @Override
    public synchronized int next() {
        currentEvenValue++;
        Thread.yield(); // can make failure faster if this program has Thread issue
        currentEvenValue++;
        return currentEvenValue;
    }
}
