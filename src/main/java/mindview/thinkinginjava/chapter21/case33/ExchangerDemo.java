package mindview.thinkinginjava.chapter21.case33;

import mindview.thinkinginjava.chapter15.caseX.generator.BasicGenerator;
import mindview.thinkinginjava.chapter21.case32.model.Fat;
import mindview.thinkinginjava.chapter21.case33.runnable.ExchangerConsumerRunnable;
import mindview.thinkinginjava.chapter21.case33.runnable.ExchangerProducerRunnable;

import java.util.List;
import java.util.concurrent.*;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class ExchangerDemo {


    public static void main(String[] args) throws InterruptedException {
        int size = 1220;
        int delay = 5;
        Exchanger<List<Fat>> exchanger = new Exchanger<>();
        List<Fat> producerList = new CopyOnWriteArrayList<>(),
                consumerList = new CopyOnWriteArrayList<>();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new ExchangerProducerRunnable<>(BasicGenerator.create(Fat.class), exchanger, producerList, size));
        executorService.execute(new ExchangerConsumerRunnable<>(exchanger, consumerList));
        TimeUnit.SECONDS.sleep(delay);
        executorService.shutdownNow();
    }

}
