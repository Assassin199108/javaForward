package com.wangwei.forward.flink.sink;

import com.alibaba.fastjson.JSON;
import com.wangwei.forward.domain.NettyReadInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;

/**
 * 对计算后的信息进行输出
 *
 * @author wangwei
 */
@Slf4j
public class SinkActivityInfo implements SinkFunction<Tuple2<Long, NettyReadInfo>> {

    private static final long serialVersionUID = -1248621632542478226L;

    @Override
    public void invoke(Tuple2<Long, NettyReadInfo> value, Context context) {
        log.warn(JSON.toJSONString(value));
    }
}
