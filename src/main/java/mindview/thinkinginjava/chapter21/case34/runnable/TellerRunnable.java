package mindview.thinkinginjava.chapter21.case34.runnable;

import mindview.thinkinginjava.chapter21.case34.collection.CustomerLine;
import mindview.thinkinginjava.chapter21.case34.model.Customer;

import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class TellerRunnable implements Runnable, Comparable<TellerRunnable> {
    private boolean serving = true;
    private CustomerLine line;
    private int serviceCount = 0;

    private static int counter = 0;
    private final int id = counter++;

    public TellerRunnable(CustomerLine line) {
        this.line = line;
    }


    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Customer customer = line.take();
                TimeUnit.MILLISECONDS.sleep(customer.getServiceTime());
                synchronized (this){
                    serviceCount++;
                    while (!serving){
                        wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println(this + " is interrupted");
        }
        System.out.println(this + " is terminating");
    }

    @Override
    public int compareTo(TellerRunnable other) {
        if (serviceCount < other.serviceCount) {
            return -1;
        } else if (serviceCount > other.serviceCount) {
            return 1;
        }
        return 0;
    }

    public synchronized void doOtherThings() {
        serviceCount = 0;
        serving = false;
    }

    public synchronized void backToWork() {
        assert !serving : "the teller is working";
        serving = true;
        notifyAll();// 通知结束阻塞
    }

    @Override
    public String toString() {
        return "Teller: " + id;
    }

    public String shortString(){
        return "T: " + id;
    }
}
