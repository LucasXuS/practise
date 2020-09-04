package mindview.thinkinginjava.chapter21.case13.runnable;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-09-04
 * @description: 抽象函数，为了利用多态，方便写测试程序用的。
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
