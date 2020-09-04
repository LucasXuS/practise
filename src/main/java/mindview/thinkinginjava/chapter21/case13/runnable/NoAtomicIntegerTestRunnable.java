package mindview.thinkinginjava.chapter21.case13.runnable;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-09-04
 * @description: 一般情况，与AtomicInteger相对，两次使用i++显然是线程不安全的。
 */
public class NoAtomicIntegerTestRunnable extends IntegerTestRunnable  {
    private int i = 0;

    // public synchronized int getValue()
    @Override
    public int getValue() {
        return i;
    }


    @Override
    protected void evenIncrement() {
        i++;
        i++;
    }
}
