package com.wangwei.forward.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 定义装苹果的篮子
 * @author Administrator
 */
public class BlockingQueueTest {

    /**
     * 篮子，能够装納3个苹果
     */
    ArrayBlockingQueue<String> basket = new ArrayBlockingQueue<String>(3);

    /**
     * 生成苹果，放入篮子
     *
     * @throws InterruptedException
     */
    private void produce() throws InterruptedException {
        //put方法放入一个苹果，若basket满了，等到basket有位置
        basket.put("An Apple");
    }

    /**
     * 消费苹果，从篮子中取走
     * @return 苹果
     * @throws InterruptedException
     */
    private String consume() throws InterruptedException {
        // get方法取出一个苹果，若basket为空，等到basket有苹果为止
        return basket.take();
    }

    /**
     * 获得苹果数量
     *
     * @return int
     */
    public int getAppleNum(){
        return basket.size();
    }

    /**
     * 测试 生产 消费方式
     */
    public static void testBasket(){
        BlockingQueueTest blockingQueueTest = new BlockingQueueTest();

        // 多线程生产者
        Runnable product = () -> {
            try {
                while (true){
                    // 生产苹果
                    System.out.println(String.format("生产者准备生产苹果：%s",System.currentTimeMillis()));
                    blockingQueueTest.produce();
                    System.out.println(String.format("生产者生产苹果完毕：%s",System.currentTimeMillis()));
                    System.out.println(String.format("生产完后有苹果：%d 个",blockingQueueTest.getAppleNum()));
                    // 休眠300ms
                    Thread.sleep(300);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        };

        // 多线程消费者
        Runnable consume = () -> {
            try {
                while (true){
                    System.out.println(String.format("消费者准备消费苹果： %s",System.currentTimeMillis()));
                    blockingQueueTest.consume();
                    System.out.println(String.format("消费者消费苹果完毕：%s",System.currentTimeMillis()));
                    System.out.println(String.format("消费完后有苹果：%d 个",blockingQueueTest.getAppleNum()));
                    Thread.sleep(1000);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        };

        //创建一个可缓存线程池，应用中存在的线程数可以无限大
        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.submit(product);
        executorService.submit(consume);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }

    public static void main(String[] args){
        testBasket();
    }

}
