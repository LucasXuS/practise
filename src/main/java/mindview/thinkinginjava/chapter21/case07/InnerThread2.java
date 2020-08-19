package mindview.thinkinginjava.chapter21.case07;

import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-08-01
 * @description: 示例2：直接使用匿名内部类
 */
public class InnerThread2 {
    private int countDown = 5;
    private Thread t;
    public InnerThread2(String name){
        t = new Thread(name){
            @Override
            public void run() {
                try {
                    while (true) {
                        System.out.println(this);
                        if (--countDown == 0) {
                            return;
                        }
                        TimeUnit.MILLISECONDS.sleep(100);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public String toString() {
                return getName() + ":(" + countDown + ")";
            }
        };
        t.start();
    }
}
