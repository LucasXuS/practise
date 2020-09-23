package mindview.thinkinginjava.chapter21.case34;

import mindview.thinkinginjava.chapter21.case34.collection.CustomerLine;
import mindview.thinkinginjava.chapter21.case34.runnable.CustomerGeneratorRunnable;
import mindview.thinkinginjava.chapter21.case34.runnable.TellerManagerRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class BankTellerSimulationDemo {

    private static int periodTime = 1000;
    private static int MAX_SIZE = 50;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CustomerLine line = new CustomerLine(MAX_SIZE);
        executorService.execute(new CustomerGeneratorRunnable(line));
        executorService.execute(new TellerManagerRunnable(line, executorService, periodTime));
        TimeUnit.SECONDS.sleep(25);
        executorService.shutdownNow();
    }
}
