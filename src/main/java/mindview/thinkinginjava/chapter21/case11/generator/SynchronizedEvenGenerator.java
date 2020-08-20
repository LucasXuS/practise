package mindview.thinkinginjava.chapter21.case11.generator;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class SynchronizedEvenGenerator extends IntGenerator{
    private int currentEvenValue= 0;
    @Override
    public synchronized int next() {
        // 这一步操作很危险
        currentEvenValue++;
        // 在这里运行此句可以增大失败的可能性
        Thread.yield();
        currentEvenValue++;
        return currentEvenValue;
    }
}
