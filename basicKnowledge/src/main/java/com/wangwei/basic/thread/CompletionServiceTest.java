package com.wangwei.basic.thread;

import java.util.concurrent.*;

public class CompletionServiceTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ThreadGroup threadGroup =
                new ThreadGroup(Thread.currentThread().getThreadGroup(), "我的组");
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                200,
                100L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(100),
                (runnable) -> new Thread(
                        threadGroup, runnable, "CompletionService -> :"),
                new ThreadPoolExecutor.AbortPolicy());

        CompletionService<String> completionService =
                new ExecutorCompletionService<>(threadPoolExecutor);

        Callable<String> callable = () -> {
            try {
                Thread.sleep(1000);
                System.out.println("线程1睡觉");

                return "1睡好了";
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "";
        };

        Callable<String> Callable2 = () -> {
            try {
                Thread.sleep(2000);
                System.out.println("线程2睡觉");

                return "2睡好了";
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "";
        };

        completionService.submit(callable);
        completionService.submit(Callable2);

        Future<String> future = completionService.take();
        String s = future.get();
        System.out.println(s);
    }

}
