package mindview.thinkinginjava.chapter21.case13.runnable;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
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
