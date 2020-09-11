package mindview.thinkinginjava.chapter21.case23;

import mindview.thinkinginjava.chapter21.case23.model.Meal;
import mindview.thinkinginjava.chapter21.case23.runnable.ChefRunnable;
import mindview.thinkinginjava.chapter21.case23.runnable.WaitPersonRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class RestaurantDemo {
    public Meal meal;
    public final ChefRunnable chefRunnable = new ChefRunnable(this);
    public final WaitPersonRunnable waitPersonRunnable = new WaitPersonRunnable(this);
    public ExecutorService executorService = Executors.newCachedThreadPool();

    public RestaurantDemo() {
        executorService.execute(chefRunnable);
        executorService.execute(waitPersonRunnable);
    }

    public static void main(String[] args) {
        new RestaurantDemo();
    }
}
