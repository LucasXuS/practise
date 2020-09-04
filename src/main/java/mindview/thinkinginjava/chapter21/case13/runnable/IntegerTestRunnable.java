package mindview.thinkinginjava.chapter21.case13.runnable;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public abstract class IntegerTestRunnable implements Runnable {

    @Override
    public void run() {
        while (true) {
            evenIncrement();
        }
    }

    protected abstract void evenIncrement();

    public abstract int getValue();
}
