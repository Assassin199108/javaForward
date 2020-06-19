package com.wangwei.basic.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class MyNioBlockedClient {

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();

        InetSocketAddress socketAddress =
                new InetSocketAddress("localhost", 8080);

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        ByteBuffer serverBuffer = ByteBuffer.allocateDirect(1024);

        socketChannel.connect(socketAddress);

        while (true) {
            byteBuffer.clear();
            byteBuffer.put("客户端又来了".getBytes());
            byteBuffer.flip();
            socketChannel.write(byteBuffer);

            serverBuffer.clear();
            socketChannel.read(serverBuffer);
            byte[] bytes = new byte[serverBuffer.position()];
            for (int i = 0; i < serverBuffer.position(); i++) {
                bytes[i] = serverBuffer.get(i);
            }

            System.out.println("狼来了" + new String(bytes));
        }
    }

}
