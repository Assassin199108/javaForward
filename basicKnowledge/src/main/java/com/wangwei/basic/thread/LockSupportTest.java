package com.wangwei.basic.thread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {
    static Queue<Integer> providerList = new LinkedList<>();
    static Queue<Integer> consumeList = new LinkedList<>();
    static Thread providerThread;
    static Thread consumeThread;
    static {
        providerList.add(1);
    }

    public static void main(String[] args) {
        Runnable providerRun = () -> {
            while (true) {
                while (providerList.isEmpty()) {
                    LockSupport.park();
                }

                Integer poll = providerList.poll();
                if (poll >= 100) {
                    LockSupport.unpark(consumeThread);
                    break;
                }
                System.out.println("生产者:"+poll);
                consumeList.add(poll + 1);
                LockSupport.unpark(consumeThread);
            }
        };

        Runnable consumeRun = () -> {
            while (true) {
                while (consumeList.isEmpty()) {
                    LockSupport.park();
                }

                Integer poll = consumeList.poll();
                if (poll >= 100) {
                    LockSupport.unpark(providerThread);
                    break;
                }

                System.out.println("消费者:"+poll);
                providerList.add(poll + 1);
                LockSupport.unpark(providerThread);
            }
        };

        providerThread = new Thread(providerRun);
        providerThread.start();
        consumeThread = new Thread(consumeRun);
        consumeThread.start();

    }

}
