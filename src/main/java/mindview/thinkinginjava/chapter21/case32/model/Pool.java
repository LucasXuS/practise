package mindview.thinkinginjava.chapter21.case32.model;

import java.util.ArrayList;
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
    private volatile  boolean[] checkedOut;
    private Semaphore available;

    public Pool(Class<T> classObject, int size){
        this.size = size;
        checkedOut = new boolean[size];
    }


}
