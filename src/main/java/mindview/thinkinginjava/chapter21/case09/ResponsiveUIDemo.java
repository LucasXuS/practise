package mindview.thinkinginjava.chapter21.case09;


/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-08-19
 * @description: 模仿按下按钮以后的反馈
 */
public class ResponsiveUIDemo extends Thread {
    public static void main(String[] args) {
        onlick();
    }

    public static void onlick() {
        new ResponsiveUI();
        // 按下按钮以后就给出反馈
        System.out.println("click finished");
    }
}
