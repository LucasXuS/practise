package mindview.thinkinginjava.chapter21.case18;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class EntranceRunnable implements Runnable {

    private static Count count = new Count();
    private static List<EntranceRunnable> runnableList = new ArrayList<>();
    private int number = 0;
    private final int id;

    private static volatile boolean canceled = false;

    public static void cancel(){
        canceled = true;
    }

    public EntranceRunnable(int id){
        this.id = id;
        runnableList.add(this);
    }

    @Override
    public void run() {
        while (!canceled){
            synchronized (this){
                number++;
            }
            System.out.println(this + " Total:" + count.increment());
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("stopping " + this);
    }

    public synchronized int getValue(){
        return number;
    }

    @Override
    public String toString(){
        return "Entrance " + id + ": " + getValue();
    }

    public static int getTotalCount(){
        return count.value();
    }

    public static int sumEntrances(){
        int sum = 0;
        for(EntranceRunnable runnable : runnableList){
            sum += runnable.getValue();
        }
        return sum;
    }
}
