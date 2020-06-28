package com.wangwei.forward.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.compression.Bzip2Decoder;
import io.netty.handler.codec.compression.Bzip2Encoder;
import org.apache.flink.shaded.akka.org.jboss.netty.buffer.ChannelBuffer;

import java.io.IOException;
import java.util.List;


public class NettyCodecAdapter {

    private final ChannelHandler encoder = new InternalEncoder();

    private final ChannelHandler decoder = new InternalDecoder();


    public NettyCodecAdapter() {
    }

    public ChannelHandler getEncoder() {
        return encoder;
    }

    public ChannelHandler getDecoder() {
        return decoder;
    }

    public static class InternalEncoder extends Bzip2Encoder {

    }

    public static class InternalDecoder extends Bzip2Decoder {

        @Override
        protected void decode(ChannelHandlerContext ctx, ByteBuf input, List<Object> out) throws Exception {

            try {
                // decode object.
                do {
                    try {
                        super.decode(ctx, input, out);
                    } catch (IOException e) {
                        throw e;
                    }
                } while (input.writerIndex() - input.readerIndex() > 0);
            } finally {
            }
        }
    }
}