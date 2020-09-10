package mindview.thinkinginjava.chapter21.case20.runnable;

import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class NeedsCleanUpRunnable implements Runnable {

    private volatile double d = 1.0;

    private class NeedsCleanUp {
        private final int id;

        public NeedsCleanUp(int id) {
            this.id = id;
            System.out.println("needs clean up: " + id);
        }

        public void cleanUp() {
            System.out.println("Cleaning up: " + id);
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                NeedsCleanUp n1 = new NeedsCleanUp(1);
                try {
                    System.out.println("sleeping");
                    TimeUnit.SECONDS.sleep(1);
                    NeedsCleanUp n2 = new NeedsCleanUp(2);
                    try {
                        System.out.println("calculating");
                        for (int i = 1; i < 250000; i++) {
                            d = d + (Math.PI + Math.E) / d;
                        }
                        System.out.println("time consuming operations is done");
                    } finally {
                        n2.cleanUp();
                    }
                } finally {
                    n1.cleanUp();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting via InterruptedException");
        }
    }
}
