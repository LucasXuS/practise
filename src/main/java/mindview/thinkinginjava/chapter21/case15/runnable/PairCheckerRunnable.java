package mindview.thinkinginjava.chapter21.case15.runnable;

import mindview.thinkinginjava.chapter21.case15.manager.PairManager;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-09-04
 * @description: 检查线程，和用于数据自增的PairManipulatorRunnable一样，是分开的独立线程，用于检查x,y是否相等
 */
public class PairCheckerRunnable implements Runnable {

    private PairManager pm;

    public PairCheckerRunnable(PairManager pm){
        this.pm = pm;
    }

    @Override
    public void run() {
        while (true){
            pm.checkCounter.incrementAndGet();
            pm.getPair().checkState();
        }
    }
}
