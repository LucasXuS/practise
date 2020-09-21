package mindview.thinkinginjava.chapter21.case30.runnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class PrioritizedTaskRunnable implements Runnable, Comparable<PrioritizedTaskRunnable> {
    private static int counter = 0;
    private final int id = counter++;
    private Random random = new Random(47);
    protected List<PrioritizedTaskRunnable> sequence = new ArrayList<>();
    private final int priority;


    public PrioritizedTaskRunnable(int priority) {
        this.priority = priority;
        sequence.add(this);
    }


    @Override
    public int compareTo(PrioritizedTaskRunnable that) {
        if (this.priority > that.priority) {
            return -1;
        } else if (this.priority < that.priority) {
            return 1;
        }
        return 0;
    }

    @Override
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(random.nextInt(250));
        } catch (InterruptedException e) {
        }
        System.out.println(this);
    }

    @Override
    public String toString() {
        return String.format("[%1$-3d]", priority) + " Task " + id;
    }

    public String summary() {
        return "(" + id + ":" + priority + ")";
    }

    public static class EndSentinelRunnable extends PrioritizedTaskRunnable {
        private ExecutorService exec;

        public EndSentinelRunnable(ExecutorService e) {
            super(-1);
            exec = e;
        }

        @Override
        public void run() {
            System.out.println();
            for (PrioritizedTaskRunnable runnable : sequence) {
                System.out.print(runnable.summary() + " ");
            }
            System.out.println();
            System.out.println(this + " Calling shutdownNow()");
            exec.shutdownNow();
        }
    }
}
