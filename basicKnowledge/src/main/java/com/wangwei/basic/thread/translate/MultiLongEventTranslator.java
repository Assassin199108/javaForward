package com.wangwei.basic.thread.translate;

import com.lmax.disruptor.EventTranslatorTwoArg;
import com.wangwei.basic.thread.event.LongEvent;

/**
 * 多项事件驱动转换器
 *
 * @author wangwei
 */
public class MultiLongEventTranslator implements EventTranslatorTwoArg<LongEvent, Long, Long> {

    @Override
    public void translateTo(LongEvent event, long sequence, Long arg0, Long arg1) {
        event.setNumber(arg0 + arg1);
    }

}
