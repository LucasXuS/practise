package mindview.thinkinginjava.chapter21.case14;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-09-04
 * @description: 测试生成器程序，理论上，SerialNumberGenerator不可能使得CircularSet里面有重复的存值。一旦发现重复的存值我们就退出程序。
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
