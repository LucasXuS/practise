package mindview.thinkinginjava.chapter21.case35.model;

import mindview.thinkinginjava.chapter19.caseX.enu.Food;
import mindview.thinkinginjava.chapter21.case35.runnable.CustomerRunnable;
import mindview.thinkinginjava.chapter21.case35.runnable.WaitPersonRunnable;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class Order {
    private final CustomerRunnable customerRunnable;
    private final WaitPersonRunnable waitPersonRunnable;
    private final Food food;
    private static int counter = 0;
    private final int id = counter++;

    public Order(CustomerRunnable c, WaitPersonRunnable w, Food f) {
        customerRunnable = c;
        waitPersonRunnable = w;
        food = f;
    }

    public Food getFood() {
        return food;
    }

    public CustomerRunnable getCustomerRunnable() {
        return customerRunnable;
    }

    public WaitPersonRunnable getWaitPersonRunnable() {
        return waitPersonRunnable;
    }

    @Override
    public String toString() {
        return "Order: " + id + " item: " + food +
                " for: " + customerRunnable +
                " served by: " + waitPersonRunnable;
    }
}
