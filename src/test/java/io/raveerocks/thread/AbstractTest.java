package io.raveerocks.thread;

public abstract class AbstractTest implements Runnable{

    protected abstract void test();

    @Override
    public void run() {
        test();
    }

}
