package mindview.thinkinginjava.chapter21.case19.runnable;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.SocketChannel;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class NIOBlockRunnable implements Runnable {

    private final SocketChannel sc;

    public NIOBlockRunnable(SocketChannel sc) {
        this.sc = sc;
    }

    @Override
    public void run() {
        try {
            System.out.println("Waiting for read() in " + this);
            sc.read(ByteBuffer.allocate(1));
        } catch (ClosedByInterruptException e) {
            System.out.println("ClosedByInterruptException from NIO");
        } catch (AsynchronousCloseException e) {
            System.out.println("AsynchronousCloseException from NIO");
        } catch (IOException e) {
            System.out.println("InterruptedException from NIO");
        }
        System.out.println("Exiting from NIOBlockRunnable.run() " + this);
    }
}
