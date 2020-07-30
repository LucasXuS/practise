package mindview.thinkinginjava.chapter21.case05;

import mindview.thinkinginjava.chapter21.case05.runnable.ADaemon;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-07-29
 * @description: 示例4：线程死亡会杀死的相当彻底，即便run()中包含finally也不会执行，
 * 这种资源的释放强于return以及throw
 */
public class DaemonsDontRunFinally {
    public static void main(String[] args) {
        Thread t = new Thread(new ADaemon());
        t.setDaemon(true);
        t.start();
    }
}
