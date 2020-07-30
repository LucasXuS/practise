package mindview.thinkinginjava.chapter21.case05.factory;

import java.util.concurrent.ThreadFactory;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-07-29
 * @description: 为了创建特定的对象（设置为后台线程的新线程，我们直接使用工厂模式）
 * // jdk本身就有工厂模式创建新线程的操作，ThreadFactory
 */
public class DaemonThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    }
}
