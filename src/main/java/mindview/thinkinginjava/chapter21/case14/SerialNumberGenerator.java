package mindview.thinkinginjava.chapter21.case14;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-09-04
 * @description: 序列号生成。本例是不安全的。return serialNumber++ 经历了自加和返回两次操作，是线程不安全的。除非加synchronized关键词
 */
public class SerialNumberGenerator {

    // 存在静态域，不以不同的对象为转移，应该是唯一值。
    private static volatile int serialNumber = 0;

    public synchronized static int nextSerialNumber() {
        return serialNumber++;
    }
}
