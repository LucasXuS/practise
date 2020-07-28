package mindview.thinkinginjava.chapter21.case02;

import mindview.thinkinginjava.chapter21.case02.callable.TaskWithResult;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-07-28
 * @description: callable的基本使用，Executor同样可以作为中间层连接callable和主线程，返回值用Future<T>包装
 */
public class CallableDemo {
    // 获取线程函数的返回值
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ArrayList<Future<String>> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            results.add(executorService.submit(new TaskWithResult(i)));
        }
        for (Future<String> future : results) {

            // get()函数在 callable.call()函数运行完毕之前处在一种阻塞态，直到运行完毕才会继续，
            // 在单线程同时直接主线程调用get的时候，有一种非异步调用的感觉
            // 查看是否完毕，可以使用future.isDone();
            try {
                System.out.println(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } finally {
                executorService.shutdown();
            }
        }

        // 关于isDone的写法，我唯一想到的提升效率的方法如下：
        while (results.size() > 0) {

            // 轮询中迭代器不允许删除元素，只能提前拿出来
            Future<String> temp = null;
            for (Future<String> future : results) {
                if (future.isDone()) {
                    temp = future;
                    break;
                }
            }
            if (temp != null) {
                try {
                    System.out.println(temp.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                } finally {
                    executorService.shutdown();
                }
                results.remove(temp);
            }
        }

    }
}
