package mindview.thinkinginjava.chapter21.case35.model;

import mindview.thinkinginjava.chapter19.caseX.enu.Food;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class Plate {
    private final Order order;
    private final Food food;
    private static int counter = 0;
    private final int id = counter++;

    public Plate(Order o, Food f) {
        order = o;
        food = f;
    }

    public Food getFood() {
        return food;
    }

    @Override
    public String toString() {
        return food.toString();
    }

    public Order getOrder() {
        return order;
    }
}
