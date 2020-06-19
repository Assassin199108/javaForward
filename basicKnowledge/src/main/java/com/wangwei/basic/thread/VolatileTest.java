package com.wangwei.basic.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VolatileTest {

    /**
     * 全局变量
     * 查看现场是否安全
     */
    public volatile int i;

    public ExecutorService executor1 = Executors.newFixedThreadPool(100, r ->
            new Thread(r, "executor1"));

    public ExecutorService executor2 = Executors.newFixedThreadPool(100, r ->
            new Thread(r, "executor2"));

    @Unsafe
    public static void main(String[] args) throws InterruptedException {
        VolatileTest v = new VolatileTest();
        CountDownLatch countDownLatch = new CountDownLatch(20);

        Runnable runnable = () -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            v.increaMent();
            countDownLatch.countDown();
        };

        for (int i = 0; i < 10; i++) {
            v.executor1.execute(runnable);

            v.executor2.execute(runnable);
        }

        countDownLatch.await();

        System.out.println(v.getI());

        v.executor1.shutdown();
        v.executor2.shutdown();
    }

    private Integer getI() {
        return i;
    }

    private void increaMent(){
        i++;
    }

}
