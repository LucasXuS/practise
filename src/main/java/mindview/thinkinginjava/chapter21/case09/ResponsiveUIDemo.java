package mindview.thinkinginjava.chapter21.case09;


/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-08-19
 * @description: 模仿按下按钮以后的反馈  这个函数的主函数模拟ui界面，并执行按下按钮的操作
 */
public class ResponsiveUIDemo extends Thread {
    public static void main(String[] args) {
        onclick();
    }

    public static void onclick() {
        new ResponsiveUIThread();
        // 按下按钮以后就给出反馈
        System.out.println("click finished");
    }
}
