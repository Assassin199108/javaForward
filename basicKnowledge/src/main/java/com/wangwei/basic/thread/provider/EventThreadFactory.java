package com.wangwei.basic.thread.provider;

import java.util.concurrent.ThreadFactory;

public class EventThreadFactory implements ThreadFactory {

    public static Integer i = 0;

    @Override
    public Thread newThread(Runnable r) {
        i++;
        Thread thread = new Thread(r);
        thread.setName("基础类线程信息: " + i + ":");
        return thread;
    }
}
