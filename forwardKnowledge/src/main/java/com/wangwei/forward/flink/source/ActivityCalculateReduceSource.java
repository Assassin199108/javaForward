package com.wangwei.forward.flink.source;

import com.wangwei.forward.domain.NettyReadInfo;
import com.wangwei.forward.netty.codec.NettyCodecAdapter;
import com.wangwei.forward.netty.inbound.MyServerCollectChannel;
import com.wangwei.forward.netty.outbound.MyOutboundChannelHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.json.JsonObjectDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;

/**
 * 资源生成器
 *
 * @author wangwei
 */
@Slf4j
public class ActivityCalculateReduceSource extends RichParallelSourceFunction<Tuple2<Long, NettyReadInfo>> {

    private static final long serialVersionUID = -922039785304792925L;

    public static SourceContext<Tuple2<Long, NettyReadInfo>> sourceContext;

    private ServerBootstrap serverBootstrap;

    private ChannelFuture future;

    private NioEventLoopGroup bossGroup;

    private NioEventLoopGroup workerGroup;

    private Boolean isRunning;

    @Override
    public void run(SourceContext<Tuple2<Long, NettyReadInfo>> ctx) throws InterruptedException {
        sourceContext = ctx;
        while (isRunning) {
            Thread.sleep(500);
        }
    }

    @Override
    public void cancel() {
        log.error("任务取消");

        isRunning = false;
    }

    @Override
    public void close() throws Exception {
        super.close();

        if (future != null) {
            future.channel().closeFuture().sync();
        }
        if (bossGroup != null) {
            bossGroup.shutdownGracefully();
        }
        if (workerGroup != null) {
            workerGroup.shutdownGracefully();
        }
    }

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);

        isRunning = true;

        if (serverBootstrap == null) {
            log.warn("初始化服务器");
            synchronized (ActivityCalculateReduceSource.class) {
                if (serverBootstrap == null) {
                    serverBootstrap = new ServerBootstrap();

                    bossGroup = new NioEventLoopGroup(2);
                    workerGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors());

                    serverBootstrap.group(bossGroup, workerGroup)
                            .channel(NioServerSocketChannel.class)
                            .childHandler(new ChannelInitializer<SocketChannel>() {
                                @Override
                                protected void initChannel(SocketChannel ch) throws Exception {
                                    ch.pipeline()
                                            .addLast(new StringDecoder())
                                            .addLast(new StringEncoder())
                                            .addLast(new LineBasedFrameDecoder(8192))
                                            .addLast(new MyOutboundChannelHandler())
                                            .addLast(new MyServerCollectChannel());
                                }
                            });
                    this.future = serverBootstrap.bind(9999).sync();
                }
            }
        }

        System.out.println("启动成功");
    }
}
