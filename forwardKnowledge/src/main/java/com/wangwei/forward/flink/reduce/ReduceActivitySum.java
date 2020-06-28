package com.wangwei.forward.flink.reduce;

import com.wangwei.forward.domain.NettyReadInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.tuple.Tuple2;

/**
 * 活动计算 生成新对象
 *
 * @author wangwei
 */
@Slf4j
public class ReduceActivitySum implements ReduceFunction<Tuple2<Long, NettyReadInfo>> {

    private static final long serialVersionUID = 6191425795504680098L;

    @Override
    public Tuple2<Long, NettyReadInfo> reduce(Tuple2<Long, NettyReadInfo> value1, Tuple2<Long, NettyReadInfo> value2) {
        log.info("开始计算");
        if (value1.f0.equals(value2.f0)) {
            NettyReadInfo nettyReadInfo = new NettyReadInfo();

            nettyReadInfo.setId(value1.f0);
            nettyReadInfo.setNum(value1.f1.getNum() + value2.f1.getNum());

            return Tuple2.of(value1.f0, nettyReadInfo);
        }

        NettyReadInfo nettyReadInfo = new NettyReadInfo();
        nettyReadInfo.setNum(0);
        nettyReadInfo.setId(value1.f0);
        return Tuple2.of(value1.f0, nettyReadInfo);
    }
}
