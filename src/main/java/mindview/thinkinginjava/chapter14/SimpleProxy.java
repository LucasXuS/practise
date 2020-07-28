package net.mindview.chapter14;

import java.util.concurrent.atomic.AtomicInteger;

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
