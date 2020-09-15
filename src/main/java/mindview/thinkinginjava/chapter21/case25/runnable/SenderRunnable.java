package mindview.thinkinginjava.chapter21.case25.runnable;

import java.io.IOException;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class SenderRunnable implements Runnable {

    private Random random = new Random(47);
    private PipedWriter out = new PipedWriter();

    public PipedWriter getPipedWriter() {
        return out;
    }

    @Override
    public void run() {
        try {
            while (true) {
                for (char c = 'A'; c <= 'Z'; c++) {
                    out.write(c);
                    TimeUnit.MILLISECONDS.sleep(random.nextInt(100));
                }
            }
        } catch (IOException e) {
            System.out.println(e + " Sender read exception");
        } catch (InterruptedException e) {
            System.out.println(e + " Sender sleep exception");
        }
    }
}
