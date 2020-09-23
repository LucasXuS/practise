package mindview.thinkinginjava.chapter21.case34.runnable;

import mindview.thinkinginjava.chapter21.case34.collection.CustomerLine;
import mindview.thinkinginjava.chapter21.case34.model.Customer;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class CustomerGeneratorRunnable implements Runnable {
    private CustomerLine line;
    private Random random = new Random(47);

    public CustomerGeneratorRunnable(CustomerLine line) {
        this.line = line;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(random.nextInt(300));
                line.put(new Customer(random.nextInt(1000)));
            }
        } catch (InterruptedException e) {
            System.out.println("CustomerGeneratorRunnable is interrupted");
        }
        System.out.println("CustomerGeneratorRunnable is terminating");
    }
}
