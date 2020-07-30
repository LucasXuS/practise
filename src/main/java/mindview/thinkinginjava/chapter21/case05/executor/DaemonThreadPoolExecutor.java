package mindview.thinkinginjava.chapter21.case05.executor;

import mindview.thinkinginjava.chapter21.case05.factory.DaemonThreadFactory;

import java.util.concurrent.*;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-07-29
 * @description: Executor 的定制，定制创建后台线程的Executor
 * 本代码是对DaemonFactoryDemo里面主程序的一个优化，我们可以直接改造Executor而不是通过给他传参。
 */
public class DaemonThreadPoolExecutor extends ThreadPoolExecutor {

    public DaemonThreadPoolExecutor() {
        super(0, Integer.MAX_VALUE
                , 60L, TimeUnit.SECONDS
                , new SynchronousQueue<>(), new DaemonThreadFactory());
    }
}
