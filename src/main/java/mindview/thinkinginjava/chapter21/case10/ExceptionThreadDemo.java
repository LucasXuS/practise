package mindview.thinkinginjava.chapter21.case10;

import mindview.thinkinginjava.chapter21.case10.runnable.ExceptionRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExceptionThreadDemo {
    public static void main(String[] args) {
        try {
            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.execute(new ExceptionRunnable());
        } catch (Exception e) {
            System.out.println("Exception has been handled");
        }
    }
}
