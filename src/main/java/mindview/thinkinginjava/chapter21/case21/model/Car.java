package mindview.thinkinginjava.chapter21.case21.model;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public abstract class Car {
    public abstract void waxed();

    public abstract void buffed();

    public abstract void waitForWaxing() throws InterruptedException;

    public abstract void waitForBuffing() throws InterruptedException;
}
