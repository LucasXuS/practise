package mindview.thinkinginjava.chapter21.case22.runnable;

import mindview.thinkinginjava.chapter21.case22.object.Blocker;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class Task1Runnable implements Runnable {
    public static Blocker blocker = new Blocker();

    @Override
    public void run() {
        blocker.waitingCall();
    }
}
