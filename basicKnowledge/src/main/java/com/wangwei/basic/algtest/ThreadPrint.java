package com.wangwei.basic.algtest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

public class ThreadPrint {

    private static List<Integer> list = new ArrayList<>();
    private static List<Integer> list2 = new ArrayList<>();

    private static Thread thread1;

    private static Thread thread2;

    static {
        list.add(1);
    }

    /**
     * 1：实现两个线程，使之交替打印1-100,如：两个线程分别为：Printer1和Printer2,最后输出结果为： Printer1 — 1 Printer2 一 2 Printer1 一 3 Printer2 一 4
     *
     * @param args
     */
    public static void main(String[] args) {
        ThreadPrint print = new ThreadPrint();
        print.print();
    }

    public void print() {
        String printer1 = "printer1-";
        String printer2 = "printer2-";

        Runnable runnable1 = () -> {
            while (true) {
                if (list.size() <= 0) {
                    LockSupport.park();
                }
                Integer i = list.get(0);
                if (i > 100) {
                    break;
                }

                System.out.println(printer1 + i);
                list.remove(0);
                list2.add(++i);


                LockSupport.unpark(thread2);
            }
        };

        Runnable runnable2 = () -> {
            while (true) {
                if (list2.size() <= 0) {
                    LockSupport.park();
                }

                Integer i = list2.get(0);
                if (i > 100) {
                    break;
                }

                System.out.println(printer2 + i);
                list2.remove(0);
                list.add(++i);

                LockSupport.unpark(thread1);
                if (i > 100) {
                    break;
                }
            }
        };

        thread1 = new Thread(runnable1);
        thread1.start();

        thread2 = new Thread(runnable2);
        thread2.start();
    }

}
