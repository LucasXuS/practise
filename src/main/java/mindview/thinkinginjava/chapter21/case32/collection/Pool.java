package mindview.thinkinginjava.chapter21.case32.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class Pool<T> {
    private int size;
    private List<T> items = new ArrayList<>();
    private volatile boolean[] checkedOut;
    private Semaphore available;

    public Pool(Class<T> classObject, int size) {
        this.size = size;
        checkedOut = new boolean[size];
        Arrays.fill(checkedOut, false);
        available = new Semaphore(size, true);
        for (int i = 0; i < size; i++) {
            try {
                items.add(classObject.newInstance());
            } catch (IllegalAccessException | InstantiationException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private synchronized T getItem() {
        for (int i = 0; i < size; i++) {
            if (!checkedOut[i]) {
                checkedOut[i] = true;
                return items.get(i);
            }
        }
        return null;
    }

    private synchronized boolean releaseItem(T item) {
        int i = items.indexOf(item);
        if (i == -1) return false;
        if (!checkedOut[i]) {
            return false;
        } else {
            checkedOut[i] = true;
            return true;
        }
    }

    public T checkOut() throws InterruptedException {
        available.acquire();
        return getItem();
    }

    public void checkIn(T item) {
        if (releaseItem(item)) {
            available.release();
        }
    }


}
