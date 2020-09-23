package mindview.thinkinginjava.chapter21.case33.runnable;

import mindview.thinkinginjava.chapter15.caseX.generator.interf.Generator;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class ExchangerProducerRunnable<T> implements Runnable {
    private Generator<T> generator;
    private Exchanger<List<T>> exchanger;
    private List<T> holder;
    private int size;

    public ExchangerProducerRunnable(Generator<T> g, Exchanger<List<T>> e, List<T> l, int size) {
        this.generator = g;
        this.exchanger = e;
        this.holder = l;
        this.size = size;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < size; i++) {
                holder.add(generator.next());
            }
            holder = exchanger.exchange(holder);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
