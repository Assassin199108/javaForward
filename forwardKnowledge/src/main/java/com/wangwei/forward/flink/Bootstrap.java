package com.wangwei.forward.flink;

import com.wangwei.forward.domain.NettyReadInfo;
import com.wangwei.forward.flink.reduce.ReduceActivitySum;
import com.wangwei.forward.flink.sink.SinkActivityInfo;
import com.wangwei.forward.flink.source.ActivityCalculateReduceSource;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.runtime.state.memory.MemoryStateBackend;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;


/**
 * flink引导类
 *
 * @author wangwei
 */
public class Bootstrap {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();

        executionEnvironment.setParallelism(1);
        executionEnvironment.setMaxParallelism(200);

        executionEnvironment.setBufferTimeout(-1);

        // 使用与集群
        executionEnvironment.setRestartStrategy(new RestartStrategies.FallbackRestartStrategyConfiguration());

        executionEnvironment.setStateBackend(new MemoryStateBackend());

        executionEnvironment.setStreamTimeCharacteristic(TimeCharacteristic.ProcessingTime);

        CheckpointConfig checkpointConfig = executionEnvironment.getCheckpointConfig();
        // 每条操作仅一次
        checkpointConfig.setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);
        // 1s进行分布式存储
        checkpointConfig.setCheckpointInterval(60000);
        // 存储超时操作
        checkpointConfig.setCheckpointTimeout(60000);
        // 一段时间内 同步的线程数
        checkpointConfig.setMaxConcurrentCheckpoints(1);
        // 2次同步之间的最小暂停点时间
        checkpointConfig.setMinPauseBetweenCheckpoints(30000);
        // 设置当存在较新的保存点时，作业恢复是否应回退到检查点
        checkpointConfig.setPreferCheckpointForRecovery(false);
        // 容忍检查的故障的数量
        checkpointConfig.setTolerableCheckpointFailureNumber(0);
        // 删除任务时候不自动清除检查点
        checkpointConfig.enableExternalizedCheckpoints(
                CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);


        SingleOutputStreamOperator<Tuple2<Long, NettyReadInfo>> operator = executionEnvironment
                .addSource(new ActivityCalculateReduceSource())
                .uid("collectActivityInfo");
        operator.keyBy(0)
                .window(TumblingProcessingTimeWindows.of(Time.minutes(1)))
                .reduce(new ReduceActivitySum())
                .uid("reduceActivityInfo")
                .addSink(new SinkActivityInfo())
                .uid("sinkActivityInfo")
                .setParallelism(1);

        executionEnvironment.execute("Java activity from netty transport Example");
    }
}
