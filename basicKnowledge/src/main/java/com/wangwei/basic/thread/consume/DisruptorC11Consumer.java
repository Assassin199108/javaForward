package com.wangwei.basic.thread.consume;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import com.wangwei.basic.thread.event.LongEvent;

/**
 * @author wangwei
 *
 * @see EventHandler
 * @apiNote 非池化的实现
 *
 * @see WorkHandler
 * @apiNote 池化的实现
 */
public class DisruptorC11Consumer implements EventHandler<LongEvent>, WorkHandler<LongEvent> {
    /**
     * 池化设计
     *
     * @param event
     */
    @Override
    public void onEvent(LongEvent event) {
        Long number = event.getNumber();
        number += 10;
        System.out.println("c11-池化设计的驱动消费:" + number);
    }

    /**
     * 非池化设计
     *
     * @param event
     * @param sequence
     * @param endOfBatch
     * @throws Exception
     */
    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("c11-非池化设计的序列号: " + sequence);
        System.out.println("c11-非池化设计的endOfBatch: " + endOfBatch);

        Long number = event.getNumber();
        number += 10;

        System.out.println("c11-非池化设计的驱动消费:" + number);
    }
}
