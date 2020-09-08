package mindview.thinkinginjava.chapter21.case17;

import java.util.Random;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-09-07
 * @description: ${todo}
 */
public class ThreadLocalVariableHolder {

    // 即使这是一个静态对象，但是对于每个线程来说，这个值都是独立的。因此这个值在不同的县城里面并不相同
    private static ThreadLocal<Integer> value = new ThreadLocal<Integer>(){
        Random random = new Random(47);
        @Override
        protected Integer initialValue() {
            return random.nextInt(10000);
        }
    };

    public static void increment(){
        value.set(get() + 1);
    }

    public static Integer get(){
        return value.get();
    }
}
