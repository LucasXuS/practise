package mindview.thinkinginjava.chapter21.case08;


/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-08-16
 * @description: joiner方法是个极好的控制线程之间运行顺序的函数，在线程A的函数中
 * 调用线程B的方法： b.join() 这样，线程A的该处，会等待B线程死亡，才会继续运行
 */
public class JoiningDemo {

    public static void main(String[] args) {
        Sleeper sleepy = new Sleeper("Sleepy", 1500),
                grumpy = new Sleeper("grumpy", 1500);
        // 为了确保能够在自己的函数中调用sleepy.join，需要将sleepy作为参数传进另一个线程的构造函数中
        Joiner dopey = new Joiner("Dopey", sleepy),
                doc = new Joiner("Doc", grumpy);
        grumpy.interrupt();
    }
}
