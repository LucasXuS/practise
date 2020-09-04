package mindview.thinkinginjava.chapter21.case15.runnable;

import mindview.thinkinginjava.chapter21.case15.manager.PairManager;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
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
