package mindview.thinkinginjava.chapter21.case35.runnable;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class ChefRunnable implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    @Override
    public void run() {
    }
}
