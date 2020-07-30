package mindview.thinkinginjava.chapter21.case05.runnable;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-07-29
 * @description: 任务Daemon中创建新线程所运行的任务（用分线程创造线程）
 */
public class DaemonSpawn implements Runnable {
    @Override
    public void run() {
        while (true) {
            Thread.yield();
        }
    }
}
