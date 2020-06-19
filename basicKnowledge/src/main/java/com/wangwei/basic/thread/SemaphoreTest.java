package com.wangwei.basic.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    public static void main(String[] args) throws InterruptedException {
        SemaphoreTest semaphoreTest = new SemaphoreTest();

        semaphoreTest.test();
    }

    private void test() throws InterruptedException {
        Semaphore semaphore = new Semaphore(10, true);
        CountDownLatch countDownLatch = new CountDownLatch(100);

        for (int i = 0; i < 100; i++) {
            Runnable runnable = () -> {
                while (true) {
                    if (semaphore.tryAcquire()) {
                        System.out.println(Thread.currentThread().getName() + "获取到了锁");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            countDownLatch.countDown();
                            semaphore.release();
                        }
                        break;
                    }
                }


            };
            new Thread(runnable).start();
        }

        countDownLatch.await();
    }

}
