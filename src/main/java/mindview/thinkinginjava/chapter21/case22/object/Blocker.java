package mindview.thinkinginjava.chapter21.case22.object;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class Blocker {
    public synchronized void waitingCall() {
        try {
            while (!Thread.interrupted()) {
                wait();
                System.out.print(Thread.currentThread() + " ");
            }
        } catch (InterruptedException e) {
            // ok to exit this way
        }
    }

    public synchronized void prod(){
        notify();
    }

    public synchronized void prodAll(){
        notifyAll();
    }
}
