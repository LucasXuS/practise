package mindview.thinkinginjava.chapter21.case17;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class AccessorRunnable implements Runnable {

    private final int id;

    public AccessorRunnable(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            ThreadLocalVariableHolder.increment();
            System.out.println(this);
            Thread.yield();
        }
    }

    @Override
    public String toString(){
        return "#" + id + " : " + ThreadLocalVariableHolder.get();
    }
}
