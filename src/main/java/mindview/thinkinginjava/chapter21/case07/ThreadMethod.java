package mindview.thinkinginjava.chapter21.case07;


import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-08-15
 * @description: 示例5：我们采取示例4的形式，但是放在单独的函数中，不放在构造函数中
 */
public class ThreadMethod {

    private int countDown = 5;
    private Thread t;
    private String name;

    public ThreadMethod(String name) {
        this.name = name;
    }


    public void runTask() {
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        System.out.println(this);
                        if (--countDown == 0) {
                            return;
                        }
                        TimeUnit.MILLISECONDS.sleep(10);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public String toString() {
                return t.getName() + ": " + countDown;
            }
        });
        t.start();
    }


}
