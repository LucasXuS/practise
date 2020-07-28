package net.mindview.chapter14;

public class ReadObject implements Interface {
    @Override
    public void doSomething() {
        System.out.println("doSomething");
    }

    @Override
    public void somethingElse(String args) {
        System.out.println("somethingElse " + args);
    }
}
