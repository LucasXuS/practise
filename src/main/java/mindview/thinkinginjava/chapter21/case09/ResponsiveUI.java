package mindview.thinkinginjava.chapter21.case09;

public class ResponsiveUI extends Thread {

    public ResponsiveUI() {
        setDaemon(false);
        start();
    }

    @Override
    public void run() {
        // 占用时间的操作
        double d = 10000d;
        while (d > 0) {
            d--;
        }
        // 这个功能运行完成以后就给出反馈
        System.out.println("function finished");
    }
}
