package mindview.thinkinginjava.chapter21.case11;

import mindview.thinkinginjava.chapter21.case11.generator.EvenGenerator;
import mindview.thinkinginjava.chapter21.case11.generator.MutexEvenGenerator;
import mindview.thinkinginjava.chapter21.case11.generator.SynchronizedEvenGenerator;
import mindview.thinkinginjava.chapter21.case11.runnable.EvenChecker;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-08-20
 * @description: 主程序，测试所有的偶数生成器程序
 */
public class EvenGeneratorDemo {
    public static void main(String[] args){
        //EvenChecker.test(new EvenGenerator());
        //EvenChecker.test(new SynchronizedEvenGenerator());
        EvenChecker.test(new SynchronizedEvenGenerator());
    }
}
