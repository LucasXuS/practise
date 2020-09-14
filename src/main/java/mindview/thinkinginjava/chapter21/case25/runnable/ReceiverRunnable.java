package mindview.thinkinginjava.chapter21.case25.runnable;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class ReceiverRunnable implements Runnable {

    private PipedReader in;
    public ReceiverRunnable(SenderRunnable senderRunnable) throws IOException {
        in = new PipedReader(senderRunnable.getPipedWriter());
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.print("Read: " + (char)in.read() + ". ");
            }
        } catch (IOException e) {
            System.out.println(e + " Receiver read exception");
        }
    }
}
