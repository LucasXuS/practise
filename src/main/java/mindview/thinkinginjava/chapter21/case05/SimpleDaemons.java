package net.mindview.chapter21;

import java.util.concurrent.TimeUnit;

public class SimpleDaemons implements Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread() + " " + this);
            } catch (InterruptedException e) {
                System.out.println("sleep() interrupted");
            }
        }
    }


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++){
            Thread t = new Thread(new SimpleDaemons());
            t.setDaemon(true);
            t.start();
        }
        System.out.println("main course");
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
