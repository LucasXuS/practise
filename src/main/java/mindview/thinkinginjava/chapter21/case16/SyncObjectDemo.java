package mindview.thinkinginjava.chapter21.case16;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: 虽然都有synchronized 但是 因为锁住的对象不同，所以可以相互交叠
 */
public class SyncObjectDemo {
    public static void main(String[] args){
        final DualSync ds = new DualSync();
        new Thread(ds::f).start();
        ds.g();
    }
}
