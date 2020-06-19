package com.wangwei.basic.thread;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventPoller;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.wangwei.basic.algtest.Test1;
import com.wangwei.basic.thread.consume.DisruptorC11Consumer;
import com.wangwei.basic.thread.consume.DisruptorC12Consumer;
import com.wangwei.basic.thread.consume.DisruptorC21Consumer;
import com.wangwei.basic.thread.consume.DisruptorC22Consumer;
import com.wangwei.basic.thread.event.LongEvent;
import com.wangwei.basic.thread.provider.EventThreadFactory;
import com.wangwei.basic.thread.provider.LongEventFactory;
import com.wangwei.basic.thread.translate.LongEventTranslator;
import com.wangwei.basic.thread.translate.MultiLongEventTranslator;

public class DisruptorTest {

    public static void main(String[] args) {
        // 环形队列长度,必须是2的N次方
        int bufferSize = 1024 * 1024;

        // 多生产者 阻塞策略
        Disruptor<LongEvent> eventDisruptor = new Disruptor<>(
                new LongEventFactory(),
                bufferSize,
                new EventThreadFactory(),
                ProducerType.MULTI,
                new BlockingWaitStrategy());

        serial(eventDisruptor);

        RingBuffer<LongEvent> ringBuffer = eventDisruptor.getRingBuffer();

        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();

        EventPoller<LongEvent> poller = ringBuffer.newPoller();

        ringBuffer.publishEvent(new LongEventTranslator(), 1L);
        ringBuffer.publishEvent(new MultiLongEventTranslator(), 10L, 100L);

    }

    /**
     * 并行计算逻辑
     *
     * @param disruptor
     */
    public static void parallel(Disruptor<LongEvent> disruptor) {
        disruptor.handleEventsWith(new DisruptorC11Consumer(), new DisruptorC21Consumer());
        disruptor.start();
    }

    /**
     * 串行计算
     *
     * @param disruptor
     */
    public static void serial(Disruptor<LongEvent> disruptor) {
        disruptor.handleEventsWith(new DisruptorC11Consumer()).then(new DisruptorC21Consumer());

        disruptor.start();
    }

    /**
     * 棱形计算
     *
     * @param disruptor
     */
    public static void diamond(Disruptor<LongEvent> disruptor) {
        disruptor.handleEventsWith(new DisruptorC11Consumer(), new DisruptorC12Consumer())
                .then(new DisruptorC21Consumer());

        disruptor.start();
    }

    /**
     * 链式并行
     *
     * @param disruptor
     */
    public static void chain(Disruptor<LongEvent> disruptor) {
        disruptor.handleEventsWith(new DisruptorC11Consumer()).then(new DisruptorC12Consumer());
        disruptor.handleEventsWith(new DisruptorC21Consumer()).then(new DisruptorC22Consumer());

        disruptor.start();
    }

}
