package mindview.thinkinginjava.chapter21.case06;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-08-01
 * @description: 示例2：使用Thread实现，这种更加灵活，SelfManagedRunnable实现Runnable
 * 的同时，可以继承另一个不同的类，Thread继承策略却无法实现这种灵活性。
 */
public class SelfManagedDemo {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new SelfManagedDemo();
        }
    }
}
