package com.wangwei.basic.queue;

import java.util.PriorityQueue;
import java.util.concurrent.*;

/**
 *     add        增加一个元索                     如果队列已满，则抛出一个IIIegaISlabEepeplian异常
 * 　　remove   移除并返回队列头部的元素    如果队列为空，则抛出一个NoSuchElementException异常
 * 　　element  返回队列头部的元素             如果队列为空，则抛出一个NoSuchElementException异常
 * 　　offer       添加一个元素并返回true       如果队列已满，则返回false
 * 　　poll         移除并返问队列头部的元素    如果队列为空，则返回null
 * 　　peek       返回队列头部的元素             如果队列为空，则返回null
 * 　　put         添加一个元素                      如果队列满，则阻塞
 * 　　take        移除并返回队列头部的元素     如果队列为空，则阻塞
 */
public class packge_info {

    /**
     * 非阻塞队列
     * PriorityQueue
     *
     * ConcurrentLinkedQueue
     *
     */
    protected class NonBlockingQueue{

        /**
         * 类实质上维护了一个有序列表。加入到 Queue 中的元素根据它们的天然排序（通过其 java.util.Comparable 实现）或者根据传递给构造函数的 java.util.Comparator 实现来定位。
         *
         * @return PriorityQueue
         */
        public PriorityQueue priorityQueue(){

            return null;
        }

        /**
         * 是基于链接节点的、线程安全的队列。并发访问不需要同步。因为它在队列的尾部添加元素并从头部删除它们，所以只要不需要知道队列的大小,　　　    　　
         * ConcurrentLinkedQueue 对公共集合的共享访问就可以工作得很好。收集关于队列大小的信息会很慢，需要遍历队列。
         *
         * @return ConcurrentLinkedQueue
         */
        public ConcurrentLinkedQueue concurrentLinkedQueue(){

            return null;
        }
    }

    /**
     *  1:ArrayBlockingQueue
     *  2:LinkedBlockingQueue
     *  3:PriorityBlockingQueue
     *  4:DelayQueue
     *  5:SynchronousQueue
     */
    protected class BlockingQueue{

        /**
         * 一个由数组支持的有界队列。
         *
         * @return ArrayBlockingQueue
         */
        public ArrayBlockingQueue arrayBlockingQueue(){
            return null;
        }

        /**
         * 一个由链接节点支持的可选有界队列。
         *
         * @return LinkedBlockingDeque
         */
        public LinkedBlockingDeque linkedBlockingDeque(){
            return null;
        }

        /**
         * 一个由优先级堆支持的无界优先级队列。
         *
         * @return PriorityBlockingQueue
         */
        public PriorityBlockingQueue priorityBlockingQueue(){
            return null;
        }

        /**
         * 一个由优先级堆支持的、基于时间的调度队列。
         *
         * @return DelayQueue
         */
        public DelayQueue delayQueue(){
            return null;
        }

        /**
         * 一个利用 BlockingQueue 接口的简单聚集（rendezvous）机制。
         *
         * @return SynchronousQueue
         */
        public SynchronousQueue synchronousQueue(){
            return null;
        }
    }

}
