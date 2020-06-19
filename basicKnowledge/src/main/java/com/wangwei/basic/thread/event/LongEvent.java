package com.wangwei.basic.thread.event;

import lombok.Builder;
import lombok.Data;

/**
 * 事件数据
 */
@Data
@Builder
public class LongEvent {

    private Long number;

}
