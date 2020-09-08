package mindview.thinkinginjava.chapter21.case16;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class DualSync {
    private Object syncObject = new Object();
    // synchronized函数锁住的代码块默认的为synchronized(this)
    public synchronized void f(){
        for(int i = 0; i < 5; i++){
            System.out.println("f()");
            Thread.yield();
        }
    }


    public void g(){
        synchronized (syncObject){
            for(int i = 0; i < 5; i++){
                System.out.println("g()");
                Thread.yield();
            }
        }
    }

}
