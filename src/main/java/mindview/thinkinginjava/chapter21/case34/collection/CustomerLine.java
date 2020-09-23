package mindview.thinkinginjava.chapter21.case34.collection;

import mindview.thinkinginjava.chapter21.case34.model.Customer;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class CustomerLine extends ArrayBlockingQueue<Customer> {

    public CustomerLine(int capacity) {
        super(capacity);
    }

    @Override
    public String toString() {
        if (size() == 0) {
            return "[empty]";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Customer c : this) {
            stringBuilder.append(c.toString());
        }
        return stringBuilder.toString();
    }
}
