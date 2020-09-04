package mindview.thinkinginjava.chapter21.case14;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-09-04
 * @description: 循环set，一旦满了就从index为0的地方覆盖原值继续存储
 */
public class CircularSet {

    private int len;
    private int index = 0;
    private int[] array;

    public CircularSet(int len) {
        this.len = len;
        array = new int[len];
        for (int i = 0; i < len; i++) {
            array[i] = -1;
        }
    }

    public synchronized void add(int val) {
        array[index] = val;
        index = ++index % len;
    }

    public synchronized boolean contains(int val) {
        for (int i = 0; i < len; i++) {
            if (array[i] == val) {
                return true;
            }
        }
        return false;
    }
}
