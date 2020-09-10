package mindview.thinkinginjava.chapter21.case21.runnable;

import mindview.thinkinginjava.chapter21.case21.model.Car;

import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class WaxOffRunnable implements Runnable {

    private Car car;

    public WaxOffRunnable(Car c){
        car = c;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                car.waitForWaxing();
                System.out.print("Was off! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            }
        }catch (InterruptedException e){
            System.out.println("Exiting via interrupting!");
        }
        System.out.println("Ending Wax off task!");
    }
}
