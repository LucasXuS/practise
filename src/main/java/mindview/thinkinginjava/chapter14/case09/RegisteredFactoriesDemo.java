package mindview.thinkinginjava.chapter14.case09;


import mindview.thinkinginjava.chapter14.case09.part.Part;

public class RegisteredFactoriesDemo {
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.println(Part.create());
        }
    }
}
