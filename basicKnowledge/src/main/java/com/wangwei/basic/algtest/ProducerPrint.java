package com.wangwei.basic.algtest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

public class ProducerPrint {


    /**
     *
     * 用多线程实现一个生产者和消费者模式。一个线程负责往Map里put 1-100的数字，另外一个线程负责get数字并进行累加，并打印结果。
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        ProducerPrint producerPrint = new ProducerPrint();
        producerPrint.sum();
    }

    private static Thread producer;
    private static Thread consumer;

    private static Map<Integer, Integer> map = new HashMap<>();
    private static int position = 0;
    private static int calPosition = 0;
    private static int sum = 0;

    public void sum() {
        Runnable runnable = () -> {
            for (int i = 1; i <= 100; i++) {
                map.put(i, i);
                position = i;

                LockSupport.unpark(consumer);
            }
        };


        Runnable runnable1 = () -> {
            while (true) {
                int position2 = 0;
                if (calPosition >= position) {
                    LockSupport.park();
                    position2 = position;
                }

                for (int i = calPosition; i < position2; i++) {
                    sum += map.get(i + 1);
                    System.out.println(sum);
                }

                if (position2 > 100) {
                    break;
                }
            }
        };

        producer = new Thread(runnable);
        producer.start();
        consumer = new Thread(runnable1);
        consumer.start();
    }

}
