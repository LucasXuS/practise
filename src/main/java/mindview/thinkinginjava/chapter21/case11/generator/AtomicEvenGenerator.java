package mindview.thinkinginjava.chapter21.case11.generator;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-09-04
 * @description: 利用AtomicInteger做生成器，非常安全，addAndGet(delta)是线程安全的操作。
 */
public class AtomicEvenGenerator extends IntGenerator {

    AtomicInteger i = new AtomicInteger(0);

    @Override
    public int next() {
        return i.addAndGet(2);
    }
}
