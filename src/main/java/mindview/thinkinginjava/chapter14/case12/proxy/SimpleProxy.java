package mindview.thinkinginjava.chapter14.case12.proxy;


public class SimpleProxy implements Interface {

    private Interface proxied;

    public SimpleProxy(Interface proxied) {
        this.proxied = proxied;
    }

    @Override
    public void doSomething() {

    }

    @Override
    public void somethingElse(String args) {

    }
}
