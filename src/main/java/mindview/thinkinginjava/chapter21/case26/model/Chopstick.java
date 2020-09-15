package mindview.thinkinginjava.chapter21.case26.model;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class Chopstick {

    private boolean taken = false;
    public synchronized  void take() throws InterruptedException {
        while (taken)
            wait();
        taken = true;
    }

    public synchronized void drop(){
        taken = false;
        notifyAll();
    }
}
