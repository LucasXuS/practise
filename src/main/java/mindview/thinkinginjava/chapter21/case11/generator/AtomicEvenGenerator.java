package mindview.thinkinginjava.chapter21.case11.generator;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class AtomicEvenGenerator extends IntGenerator {
    AtomicInteger i = new AtomicInteger(0);


    @Override
    public int next() {
        return i.addAndGet(2);
    }
}
