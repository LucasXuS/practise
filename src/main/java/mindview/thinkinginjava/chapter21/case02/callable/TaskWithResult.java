package mindview.thinkinginjava.chapter21.case02.callable;

import java.util.concurrent.Callable;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-07-28
 * @description: runnable(任务)是不返回任何参数的，而Callable则是带参的任务，而且要指明返回具体类型。
 */
public class TaskWithResult implements Callable<String> {
    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    // 因为继承的泛型被指明为String,所以返回值为String
    @Override
    public String call() throws Exception {
        return "result of taskWithResult is " + id;
    }
}
