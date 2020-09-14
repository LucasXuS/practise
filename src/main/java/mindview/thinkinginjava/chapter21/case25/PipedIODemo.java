package mindview.thinkinginjava.chapter21.case25;

import mindview.thinkinginjava.chapter21.case25.runnable.ReceiverRunnable;
import mindview.thinkinginjava.chapter21.case25.runnable.SenderRunnable;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class PipedIODemo {
    public static void main(String[] args) throws IOException, InterruptedException {
        SenderRunnable senderRunnable = new SenderRunnable();
        ReceiverRunnable receiverRunnable = new ReceiverRunnable(senderRunnable);
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(senderRunnable);
        executorService.execute(receiverRunnable);
        TimeUnit.SECONDS.sleep(4);
        executorService.shutdownNow();
    }
}
