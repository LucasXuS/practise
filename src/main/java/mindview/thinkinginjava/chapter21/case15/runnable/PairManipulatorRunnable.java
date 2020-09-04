package mindview.thinkinginjava.chapter21.case15.runnable;

import mindview.thinkinginjava.chapter21.case15.manager.PairManager;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-09-04
 * @description: 用于数据自增的独立线程，核心是调用PairManager的increment()
 */
public class PairManipulatorRunnable implements Runnable {

    private PairManager pm;

    public PairManipulatorRunnable(PairManager pm) {
        this.pm = pm;
    }


    @Override
    public void run() {
        while (true) {
            pm.increment();
        }
    }


    @Override
    public String toString() {
        return "pair = " + pm.getPair() + ", checkCounter = " + pm.checkCounter.get();
    }
}
