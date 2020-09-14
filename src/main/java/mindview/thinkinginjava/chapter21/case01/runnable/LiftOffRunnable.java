package mindview.thinkinginjava.chapter21.case01.runnable;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-07-27
 * @description: 基本示例
 */
//  runnable 是一个任务，在不启用Thread等类的情况下单独运行并不是多线程运行
public class LiftOffRunnable implements Runnable {

    protected int countDown = 10;// default

    // taskCount是静态变量，那么我们创建多个LiftOff对象，taskCount的数值都是共享的。
    private static int taskCount = 0;
    // id为标记，由于taskCount是共享的，所以id各不相同
    private final int id = taskCount++;

    public LiftOffRunnable() {
    }

    public LiftOffRunnable(int countDown) {
        this.countDown = countDown;
    }

    public String status() {
        return "#" + id + "(" + (countDown > 0 ? countDown : "LiftOff!") + ").";
    }

    @Override
    public void run() {
        while (countDown-- > 0) {
            System.out.print(status());
            // 要求转换转移到其他线程的语句，效果是线程转换会更加频繁
            // 但是强制要求线程转换是不可以仅仅依靠这个语句的。
            Thread.yield();
        }
    }
}
