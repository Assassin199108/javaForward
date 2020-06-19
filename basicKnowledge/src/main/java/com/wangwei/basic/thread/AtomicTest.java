package com.wangwei.basic.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {

    /**
     * 全局变量
     * 查看现场是否安全
     */
    private volatile AtomicInteger integer;

    public ExecutorService executor1 = Executors.newFixedThreadPool(100, r ->
            new Thread(r, "executor1"));

    public ExecutorService executor2 = Executors.newFixedThreadPool(100, r ->
            new Thread(r, "executor2"));

    @Safe
    public static void main(String[] args){
        AtomicTest atomicTest = new AtomicTest();
        atomicTest.setInteger(new AtomicInteger(1));

        Runnable runnable = () -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int i = atomicTest.getInteger().incrementAndGet();
            System.out.println(Thread.currentThread().getName() + ":value:" + i);
        };

        for (int i = 0; i < 10; i++) {
            atomicTest.executor1.execute(runnable);

            atomicTest.executor2.execute(runnable);
        }

        atomicTest.executor1.shutdown();
        atomicTest.executor2.shutdown();
    }

    public AtomicInteger getInteger() {
        return integer;
    }

    public void setInteger(AtomicInteger integer) {
        this.integer = integer;
    }
}
