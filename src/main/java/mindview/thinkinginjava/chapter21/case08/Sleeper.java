package mindview.thinkinginjava.chapter21.case08;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-08-16
 * @description: 通过sleep() 代表一个占用时间的线程
 */
public class Sleeper extends Thread {
    private int duration;

    public Sleeper(String name, int sleepTime) {
        super(name);
        duration = sleepTime;
        start();
    }

    @Override
    public void run() {
        // 这个仅仅是一个占用时间的线程
        try {
            sleep(duration);
        } catch (InterruptedException e) {
            System.out.println(getName() + " was interrupted.");
            System.out.println("isInterrupted() = " + isInterrupted());
            return;
        }
        System.out.println(getName() + " has awakened.");
    }
}
