package mindview.thinkinginjava.chapter21.case33.runnable;

import mindview.thinkinginjava.chapter15.caseX.generator.interf.Generator;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class ExchangerConsumerRunnable<T> implements Runnable {

    private Exchanger<List<T>> exchanger;
    private List<T> holder;
    private volatile T value;

    public ExchangerConsumerRunnable(Exchanger<List<T>> e, List<T> l) {
        this.exchanger = e;
        this.holder = l;
    }

    @Override
    public void run() {
        try {
            holder = exchanger.exchange(holder);
            for(T item : holder){
                value = item;
                holder.remove(item);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("the final value is " + value);
    }
}
