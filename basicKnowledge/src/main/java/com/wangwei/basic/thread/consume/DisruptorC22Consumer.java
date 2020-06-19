package com.wangwei.basic.thread.consume;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import com.wangwei.basic.thread.event.LongEvent;

/**
 * c12消费者
 *
 * @author wangwei
 */
public class DisruptorC22Consumer implements EventHandler<LongEvent>, WorkHandler<LongEvent> {

    @Override
    public void onEvent(LongEvent event) throws Exception {
        Long number = event.getNumber();
        number *= 20;
        System.out.println("c22-池化设计的驱动消费:" + number);
    }

    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("c22-非池化设计的序列号: " + sequence);
        System.out.println("c22-非池化设计的endOfBatch: " + endOfBatch);

        Long number = event.getNumber();
        number *= 20;

        System.out.println("c22-非池化设计的驱动消费:" + number);
    }
}
