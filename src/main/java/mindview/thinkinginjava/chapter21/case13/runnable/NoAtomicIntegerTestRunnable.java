package mindview.thinkinginjava.chapter21.case13.runnable;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
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
