package com.wangwei.basic.thread.translate;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.wangwei.basic.thread.event.LongEvent;

/**
 * 数据转换为事件的 事件驱动器
 *
 * @author wangwei
 */
public class LongEventTranslator implements EventTranslatorOneArg<LongEvent, Long> {
    @Override
    public void translateTo(LongEvent event, long sequence, Long arg0) {
        event.setNumber(arg0);
    }
}
