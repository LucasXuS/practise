package mindview.thinkinginjava.chapter21.case11.generator;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-09-04
 * @description: 这是一个反例，两次currentEvenValue++显然不是原子性操作，线程不全，为了能够更快显示出效果，我们在两次中间运行Thread.yield()
 */
public class EvenGenerator extends IntGenerator {

    private int currentEvenValue= 0;
    @Override
    public int next() {
        // 这一步操作很危险
        currentEvenValue++;
        // 在这里运行此句可以增大失败的可能性
        Thread.yield();
        currentEvenValue++;
        return currentEvenValue;
    }
}
