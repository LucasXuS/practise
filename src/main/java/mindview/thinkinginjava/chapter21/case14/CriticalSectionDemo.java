package mindview.thinkinginjava.chapter21.case14;

import mindview.thinkinginjava.chapter21.case14.manager.*;
import mindview.thinkinginjava.chapter21.case14.runnable.PairCheckerRunnable;
import mindview.thinkinginjava.chapter21.case14.runnable.PairManipulatorRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class CriticalSectionDemo {
    static void testApproaches(PairManager pMan1, PairManager pMan2) {
        PairManipulatorRunnable pmr1 = new PairManipulatorRunnable(pMan1);
        PairManipulatorRunnable pmr2 = new PairManipulatorRunnable(pMan2);
        PairCheckerRunnable pcr1 = new PairCheckerRunnable(pMan1);
        PairCheckerRunnable pcr2 = new PairCheckerRunnable(pMan2);
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(pmr1);
        executorService.execute(pmr2);
        executorService.execute(pcr1);
        executorService.execute(pcr2);
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("sleep has been interrupted");
        }

        System.out.println("pmr1 = " + pmr1 + "\npmr2 = " + pmr2);
        System.exit(0);
    }

    public static void main(String[] args){
        //testApproaches(new SynchronizedFunctionPairManager(), new SynchronizedBlockPairManager());
        testApproaches(new LockSimulateSyncFunctionPairManager(), new LockSimulateSyncBlockPairManager());
    }
}
