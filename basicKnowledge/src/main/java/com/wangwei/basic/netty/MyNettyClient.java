package com.wangwei.basic.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.util.concurrent.TimeUnit;

public class MyNettyClient {

    public static void main(String[] args) {
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();

        bootstrap
                .group(eventExecutors)
                .option(ChannelOption.TCP_NODELAY, true)
                .channel(NioSocketChannel.class)
                .remoteAddress("127.0.0.1", 8080)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new MyInboundChannelHandler())
                                .addLast(new MyOutboundChannelHandler());
                    }
                });

        ChannelFuture future = bootstrap.connect();

        boolean ret = future.awaitUninterruptibly(3000, TimeUnit.MILLISECONDS);

        if (ret && future.isSuccess()) {
            Channel channel = future.channel();


            channel.writeAndFlush("你好");

        } else if (future.isCancelled()) {
            System.out.println("挂掉了");
        } else if (future.isDone()) {
            System.out.println("完成了");
        } else if (future.isCancellable()) {
            System.out.println("可取消的");
        }
    }

}
