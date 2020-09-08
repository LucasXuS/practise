package mindview.thinkinginjava.chapter21.case19;

import mindview.thinkinginjava.chapter21.case19.runnable.IOBlockRunnable;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class CloseResourceDemo {

    public static void main(String[] args) throws IOException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(8080);
        InputStream socketInput = new Socket("localhost", 8080).getInputStream();
        executorService.execute(new IOBlockRunnable(socketInput));
        executorService.execute(new IOBlockRunnable(System.in));
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("Shutting down all threads");
        executorService.shutdownNow();
        TimeUnit.SECONDS.sleep(2);
        System.out.println("Closing " + socketInput.getClass().getName());
        socketInput.close();
        TimeUnit.SECONDS.sleep(2);
        System.out.println("Closing " + System.in.getClass().getName());
        System.in.close();

    }
}
