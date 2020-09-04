package mindview.thinkinginjava.chapter21.case15.manager;

import mindview.thinkinginjava.chapter21.case15.model.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-09-04
 * @description: 抽象函数，在这里我们实现了获取pair和保存pair，但是pair的自增需要子类各自实现。
 */
public abstract class PairManager {

    public AtomicInteger checkCounter = new AtomicInteger(0);
    protected Pair pair = new Pair();

    // 注意这里！这里是一个线程安全的list
    private List<Pair> storage = Collections.synchronizedList(new ArrayList<>());

    // 因为我们不可能把同一个对象不停存进storage，因此我们是深复制了对象再进行保存
    public synchronized Pair getPair(){
        // make a copy to keep the original safe
        return new Pair(pair.getX(), pair.getY());
    }

    protected void store(Pair p){
        storage.add(p);
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 唯一灵活的是自增操作
    public abstract void increment();
}
