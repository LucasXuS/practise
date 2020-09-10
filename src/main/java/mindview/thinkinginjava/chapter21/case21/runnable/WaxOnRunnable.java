package mindview.thinkinginjava.chapter21.case21.runnable;

import mindview.thinkinginjava.chapter21.case21.model.Car;

import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class WaxOnRunnable implements Runnable {

    private Car car;

    public WaxOnRunnable(Car c){
        car = c;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){

                System.out.print("Was on! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitForBuffing();
            }
        }catch (InterruptedException e){
            System.out.println("Exiting via interrupting!");
        }
        System.out.println("Ending Wax on task!");
    }
}
