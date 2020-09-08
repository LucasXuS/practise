package mindview.thinkinginjava.chapter21.case18;

import java.util.Random;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class Count {
    private int count = 0;
    private Random random = new Random(47);

    public synchronized int increment(){
        int temp = count;
        if(random.nextBoolean()){
            Thread.yield();
        }
        return (count = ++temp);
    }

    public synchronized int value(){
        return count;
    }

}
