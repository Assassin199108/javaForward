package com.wangwei.forward.netty.inbound;

import com.alibaba.fastjson.JSON;
import com.wangwei.forward.domain.NettyReadInfo;
import com.wangwei.forward.flink.source.ActivityCalculateReduceSource;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.java.tuple.Tuple2;

/**
 * 我的服务收集通道
 *
 * @author wangwei
 */
@Slf4j
public class MyServerCollectChannel extends SimpleChannelInboundHandler {


    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        log.info("inbound 注册");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        log.info("inbound 取消注册");
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("inbound 激活");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("inbound 取消激活");
        super.channelInactive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("inbound 读取");

        NettyReadInfo nettyReadInfo = JSON.parseObject(msg.toString(), NettyReadInfo.class);
        ActivityCalculateReduceSource.sourceContext.collect(
                Tuple2.of(nettyReadInfo.getId(), nettyReadInfo));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.info("inbound 读取完成");
        super.channelReadComplete(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        log.info("inbound 事件触发");
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        log.info("inbound 写更改");
        super.channelWritabilityChanged(ctx);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info("inbound 适配添加");
        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info("inbound 适配删除");
        super.handlerRemoved(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("inbound 异常捕获");
        super.exceptionCaught(ctx, cause);
    }
}
