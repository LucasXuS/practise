package mindview.thinkinginjava.chapter21.case11.generator;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-08-20
 * @description: 偶数生成器的抽象函数，有三部分，包括取消开关的读写，和生成数据。
 */
public abstract class IntGenerator {
    private volatile boolean canceled = false;

    public abstract int next();

    public void cancel() {
        canceled = true;
    }

    public boolean isCanceled() {
        return canceled;
    }

}
