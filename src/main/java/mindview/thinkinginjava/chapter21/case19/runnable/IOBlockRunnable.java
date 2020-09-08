package mindview.thinkinginjava.chapter21.case19.runnable;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class IOBlockRunnable implements Runnable {
    private InputStream in;

    public IOBlockRunnable(InputStream inputStream) {
        this.in = inputStream;
    }

    @Override
    public void run() {
        try {
            System.out.println("Waiting for read()");
            in.read();
        } catch (IOException e) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("InterruptedException from IO");
            } else {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Exiting from IOBlockRunnable.run()");
    }
}
