package mindview.thinkinginjava.chapter21.case13.runnable;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-09-04
 * @description: 使用AtomicInteger 生成偶数，线程安全。
 */
public class AtomicIntegerTestRunnable extends IntegerTestRunnable {
    private AtomicInteger i = new AtomicInteger(0);

    @Override
    protected void evenIncrement() {
        i.addAndGet(2);
    }

    @Override
    public int getValue() {
        return i.get();
    }
}
