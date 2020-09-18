package mindview.thinkinginjava.chapter21.case28;

import mindview.thinkinginjava.chapter21.case28.runnable.HorseRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class HorseRaceDemo {
    static final int FINISH_LINE = 40;
    private List<HorseRunnable> horseRunnableList = new ArrayList<>();
    private ExecutorService executorService = Executors.newCachedThreadPool();
    private CyclicBarrier barrier;

    public void doWork(int nHorses, final int pause){
        barrier = new CyclicBarrier(nHorses, () -> {
            StringBuilder s = new StringBuilder();
            for(int i = 0; i < FINISH_LINE; i++){
                s.append("=");
            }
            System.out.println(s);
            for(HorseRunnable horseRunnable : horseRunnableList){
                System.out.println(horseRunnable.tracks());
            }
            for (HorseRunnable horseRunnable : horseRunnableList){
                if(horseRunnable.getStrides() >= FINISH_LINE){
                    System.out.println(horseRunnable + "won!");
                    executorService.shutdownNow();
                    return;
                }
            }
            try {
                TimeUnit.MILLISECONDS.sleep(pause);
            } catch (InterruptedException e) {
                System.out.println("barrier-action sleep interrupted");
            }
        });
        for(int i = 0; i < nHorses; i++){
            HorseRunnable horseRunnable = new HorseRunnable(barrier);
            horseRunnableList.add(horseRunnable);
            executorService.execute(horseRunnable);

        }
    }

    public static void main(String[] args){
        HorseRaceDemo demo = new HorseRaceDemo();
        demo.doWork(7, 200);
    }
}
