package mindview.thinkinginjava.chapter21.case11;

import mindview.thinkinginjava.chapter21.case11.generator.EvenGenerator;
import mindview.thinkinginjava.chapter21.case11.generator.SynchronizedEvenGenerator;
import mindview.thinkinginjava.chapter21.case11.runnable.EvenChecker;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-08-20
 * @description: ${todo}
 */
public class EvenGeneratorDemo {
    public static void main(String[] args){
        //EvenChecker.test(new EvenGenerator());
        EvenChecker.test(new SynchronizedEvenGenerator());
    }
}
