package mindview.thinkinginjava.chapter21.case10.runnable;

public class ExceptionRunnable implements Runnable {
    @Override
    public void run() {
        throw new RuntimeException();
    }
}
