package mindview.thinkinginjava.chapter21.case06.thread;


// 在非常简单的情况下，可以直接从Thread继承来代替runnable赋值的方式
public class SimpleThread extends Thread {


    private int countDown = 5;
    private static int threadCount = 0;

    public SimpleThread() {
        // store the thread name
        super(Integer.toString(++threadCount));
        start();
    }

    @Override
    public String toString() {
        return "#" + getName() + "(" + countDown + "), ";
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
}
