package mindview.thinkinginjava.chapter21.case23.runnable;

import mindview.thinkinginjava.chapter21.case23.RestaurantDemo;
import mindview.thinkinginjava.chapter21.case23.model.Meal;

import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class ChefRunnable implements Runnable {

    private RestaurantDemo restaurant;
    private int count = 0;

    public ChefRunnable(RestaurantDemo r) {
        this.restaurant = r;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal != null) {
                        wait();
                    }
                }
                if (++count == 10) {
                    System.out.println("Out of food! Closing!");
                    restaurant.executorService.shutdownNow();
                }
                System.out.print("order up! ");
                synchronized (restaurant.waitPersonRunnable) {
                    restaurant.meal = new Meal(count);
                    restaurant.waitPersonRunnable.notifyAll();
                }
                TimeUnit.MILLISECONDS.sleep(100);


            }
        } catch (InterruptedException e) {
            System.out.println("chef interrupted");
        }
    }
}
