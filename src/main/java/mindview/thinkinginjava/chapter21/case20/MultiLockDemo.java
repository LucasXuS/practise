package mindview.thinkinginjava.chapter21.case20;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class MultiLockDemo {
    public synchronized void f1(int count) {
        if (count-- > 0) {
            System.out.println("f1() calling f2() with count " + count);
            f2(count);
        }
    }

    public synchronized void f2(int count) {
        if (count-- > 0) {
            System.out.println("f2() calling f1() with count " + count);
            f1(count);
        }
    }

    public static void main(String[] args){
        final MultiLockDemo multiLockDemo = new MultiLockDemo();
        new Thread(){
            @Override
            public void run() {
                super.run();
                multiLockDemo.f1(10);
            }
        }.start();
    }
}
