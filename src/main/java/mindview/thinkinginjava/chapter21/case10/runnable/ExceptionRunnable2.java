package mindview.thinkinginjava.chapter21.case10.runnable;

public class ExceptionRunnable2 implements Runnable {
    @Override
    public void run() {
        Thread t = Thread.currentThread();
        System.out.println("run() by " + t);
        System.out.println("exception handler is : " + t.getUncaughtExceptionHandler());
        throw new RuntimeException();
    }
}
