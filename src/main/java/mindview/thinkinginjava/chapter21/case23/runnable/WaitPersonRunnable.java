package mindview.thinkinginjava.chapter21.case23.runnable;

import mindview.thinkinginjava.chapter21.case23.RestaurantDemo;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class WaitPersonRunnable implements Runnable {

    private RestaurantDemo restaurant;

    public WaitPersonRunnable(RestaurantDemo r) {
        this.restaurant = r;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal == null){
                        wait();
                    }
                }
                System.out.println("Waitperson got " + restaurant.meal);
                synchronized (restaurant.chefRunnable){
                    restaurant.meal = null;
                    restaurant.chefRunnable.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("wait person interrupted");
        }
    }
}
