package mindview.thinkinginjava.chapter21.case05.runnable;


public class Daemon implements Runnable {


    private Thread[] threads = new Thread[10];

    @Override
    public void run() {
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new DaemonSpawn());
            // 注意！这里不要讲分线程设置为后台线程,但是如果设置setDaemon(false)则不是后台线程
            // threads[i].setDaemon(false);
            threads[i].start();
            System.out.println("DaemonSpawn " + i + " is started!");
        }
        // 在本线程为后台线程的情况下，看看此线程创建的分线程是否为后台线程。
        for (int i = 0; i < threads.length; i++) {
            System.out.println("threads[" + i + "].isDaemon() = "
                    + threads[i].isDaemon());
        }
        while (true) {
            Thread.yield();
        }

    }
}
