package com.wangwei.basic.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4, () -> {
            System.out.println("人员到齐");
        });

        Runnable runnable = () -> {
            try {
                Thread.sleep(100);

                System.out.println("1号休眠完毕");
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

        };
        Runnable runnable2 = () -> {
            try {
                Thread.sleep(200);

                System.out.println("2号休眠完毕");
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        };
        Runnable runnable3 = () -> {
            try {
                Thread.sleep(300);

                System.out.println("3号休眠完毕");
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        };
        Runnable runnable4 = () -> {
            try {
                Thread.sleep(400);

                System.out.println("4号休眠完毕");
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        };

        new Thread(runnable).start();
        new Thread(runnable2).start();
        new Thread(runnable3).start();
        new Thread(runnable4).start();

        System.out.println("主线程运行完毕");
    }

}
