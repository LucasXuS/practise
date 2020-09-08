package mindview.thinkinginjava.chapter21.case15.model;

import mindview.thinkinginjava.chapter21.case15.manager.PairManager;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-09-04
 * @description: 数据模型，一组数据
 */
public class Pair {
    private int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Pair() {
        this(0, 0);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void incrementX() {
        x++;
    }

    public void incrementY() {
        y++;
    }

    @Override
    public String toString() {
        return "x = " + x + ",y = " + y;
    }

    private class PairValuesNotEqualException extends RuntimeException {
        public PairValuesNotEqualException() {
            super("pair values not equal: " + Pair.this);
        }
    }

    public void checkState() {
        if (x != y) {
            throw new PairValuesNotEqualException();
        }
    }
}
