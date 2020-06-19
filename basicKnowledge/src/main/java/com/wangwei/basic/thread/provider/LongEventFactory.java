package com.wangwei.basic.thread.provider;

import com.lmax.disruptor.EventFactory;
import com.wangwei.basic.thread.event.LongEvent;

/**
 * long的事件工厂生产者
 *
 * @author wangwei
 */
public class LongEventFactory implements EventFactory<LongEvent> {

    @Override
    public LongEvent newInstance() {
        return LongEvent.builder().build();
    }

}
