package mindview.thinkinginjava.chapter21.case06.runnable;

public class SelfManagedRunnable implements Runnable {
    private int countDown = 5;
    private Thread t = new Thread(this);

    public SelfManagedRunnable() {
        // 一般情况下应该避免在构造函数中启动新线程，
        // 当构造函数特别复杂时，t可能在runnable构造函数运行完成之前就开始启动线程
        // 将产生未知的风险。所以，我们建议使用Executor，因为可以隐藏Thread
        t.start();
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(this);
            if (--countDown == 0) {
                return;
            }
        }
    }

    @Override
    public String toString() {
        return Thread.currentThread().getName() + "(" + countDown + ")";
    }
}
