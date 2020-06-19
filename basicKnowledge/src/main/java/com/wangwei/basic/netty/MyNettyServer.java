package com.wangwei.basic.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

public class MyNettyServer {

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap
                .group(eventExecutors)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .childAttr(AttributeKey.valueOf("name"), "wang")
                .localAddress(8080)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new MyInboundChannelHandler())
                                .addLast(new MyOutboundChannelHandler());
                    }
                });

        ChannelFuture channelFuture = bootstrap.bind().sync();

        channelFuture.addListener(future -> System.out.println("操作完成"));
        ServerSocketChannel channel = (ServerSocketChannel) channelFuture.channel();

        if (channel.isActive()) {

        } else if (channel.isOpen()) {

        } else if (channel.isRegistered()) {

        } else if (channel.isWritable()) {

        }
    }

}
