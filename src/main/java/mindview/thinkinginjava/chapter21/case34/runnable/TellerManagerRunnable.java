package mindview.thinkinginjava.chapter21.case34.runnable;

import mindview.thinkinginjava.chapter21.case34.collection.CustomerLine;

import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class TellerManagerRunnable implements Runnable {

    private CustomerLine line;
    private PriorityQueue<TellerRunnable> workingTellers = new PriorityQueue<>();
    private PriorityQueue<TellerRunnable> otherTellers = new PriorityQueue<>();
    private ExecutorService executorService;
    private int waitPeriod;

    public TellerManagerRunnable(CustomerLine line, ExecutorService executorService, int waitPeriod) {
        this.line = line;
        this.executorService = executorService;
        this.waitPeriod = waitPeriod;
        TellerRunnable tellerRunnable = new TellerRunnable(line);
        executorService.execute(tellerRunnable);
        workingTellers.offer(tellerRunnable);
    }


    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(waitPeriod);
                adjustTellers();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(line.toString()).append(" [");
                for (TellerRunnable tellerRunnable : workingTellers) {
                    stringBuilder.append(tellerRunnable.shortString()).append(" ");
                }
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                stringBuilder.append("]");
                System.out.println(stringBuilder.toString());
            }
        } catch (InterruptedException e) {
            System.out.println(this + " is interrupted");
        }
        System.out.println(this + " is terminating");
    }

    private void adjustTellers() {
        if (line.size() / workingTellers.size() > 2) {
            if (otherTellers.size() > 0) {
                TellerRunnable tellerRunnable = otherTellers.poll();
                if(tellerRunnable != null){
                    tellerRunnable.backToWork();
                    workingTellers.offer(tellerRunnable);
                }
            }else {
                TellerRunnable tellerRunnable = new TellerRunnable(line);
                executorService.execute(tellerRunnable);
                workingTellers.offer(tellerRunnable);
            }
            return;
        }

        if (workingTellers.size() > 1 && line.size() / workingTellers.size() < 2) {
            resignOneTeller();
        }

        if (line.size() == 0) {
            while (workingTellers.size() > 1){
                resignOneTeller();
            }
        }


    }

    public void resignOneTeller() {
        TellerRunnable tellerRunnable = workingTellers.poll();
        if(tellerRunnable != null){
            tellerRunnable.doOtherThings();
            otherTellers.offer(tellerRunnable);
        }
    }

    @Override
    public String toString() {
        return "TellerManagerRunnable";
    }

}
