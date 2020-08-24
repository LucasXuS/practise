package mindview.thinkinginjava.chapter21.case13;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class SerialCheckerDemo {
    private static final int SIZE = 10;
    private static CircularSet serials = new CircularSet(1000);

    private static ExecutorService executorService = Executors.newCachedThreadPool();

    static class SerialCheckerRunnable implements Runnable {
        @Override
        public void run() {
            int serial = SerialNumberGenerator.nextSerialNumber();
            if (serials.contains(serial)) {
                System.out.println("Duplicate: " + serial);
                System.exit(0);
            }
            serials.add(serial);
        }
    }


    public static void main(String[] args) {
        for (int i = 0; i < SIZE; i++){
            executorService.execute(new SerialCheckerRunnable());
        }
    }
}
