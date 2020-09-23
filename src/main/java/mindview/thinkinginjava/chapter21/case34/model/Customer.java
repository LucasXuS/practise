package mindview.thinkinginjava.chapter21.case34.model;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class Customer {

    private final int time;

    public Customer(int time) {
        this.time = time;
    }

    public String toString() {
        return "[" + time + "]";
    }

    public int getServiceTime(){
        return time;
    }
}
