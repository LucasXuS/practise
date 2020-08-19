package mindview.thinkinginjava.chapter21.case08;


/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-08-16
 * @description: 在此类中运行sleeper.join 确保sleeper线程死亡后，此线程类的run才能继续运行
 */
public class Joiner extends Thread {

    private Sleeper sleeper;

    public Joiner(String name, Sleeper sleeper) {
        super(name);
        this.sleeper = sleeper;
        start();
    }


    @Override
    public void run() {

        //  等待Sleeper类运行完才继续运行 join 也接受参数integer,达到设定timeout的目的
        // 不为空为无限时间，但是会有程序锁的风险
        try {
            sleeper.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }

        System.out.println(getName() + " join completed.");
    }
}
