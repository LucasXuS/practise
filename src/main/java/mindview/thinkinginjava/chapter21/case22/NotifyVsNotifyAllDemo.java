package mindview.thinkinginjava.chapter21.case22;

import mindview.thinkinginjava.chapter21.case22.runnable.Task1Runnable;
import mindview.thinkinginjava.chapter21.case22.runnable.Task2Runnable;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class NotifyVsNotifyAllDemo {


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Task1Runnable());
        }
        executorService.execute(new Task2Runnable());
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            boolean prod = true;
            @Override
            public void run() {
                if(prod){
                    System.out.print("\nnotify() ");
                    Task1Runnable.blocker.prod();
                    prod = false;
                }else {
                    System.out.print("\nnotifyAll() ");
                    Task2Runnable.blocker.prodAll();
                    prod = true;
                }
            }
        }, 400, 400);

        TimeUnit.SECONDS.sleep(5);
        timer.cancel();
        System.out.println("\nTimer canceled");
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.print("Task2Runnable.blocker.prodAll() ");
        Task2Runnable.blocker.prodAll();
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("\nShutting down");
        executorService.shutdownNow();
    }
}
