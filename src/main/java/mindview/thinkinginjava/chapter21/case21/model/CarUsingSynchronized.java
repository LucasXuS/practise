package mindview.thinkinginjava.chapter21.case21.model;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class CarUsingSynchronized extends Car{
    private boolean waxOn = false;

    public synchronized void waxed() {
        waxOn = true;
        notifyAll();
    }

    public synchronized void buffed() {
        waxOn = false;
        notifyAll();
    }

    public synchronized void waitForWaxing() throws InterruptedException {
        while (!waxOn) {
            // wait() 会让线程暂时挂起，只有别的线程notify或者notifyAll时才会继续运行
            // ，检查waxOn的状态
            // 在运行wait()的时候，对象锁会被释放（sleep 与 yield都不会释放锁）
            // while内的代码应该尽量简短，如果里面代码过长，使得notify在wait之前运行，程序产生死锁。
            wait();
        }
    }

    public synchronized void waitForBuffing() throws InterruptedException {
        while (waxOn) {
            wait();
        }
    }
}
