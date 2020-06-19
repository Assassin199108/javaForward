package com.wangwei.basic.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class MyNioBlockedServer {

    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel =
                ServerSocketChannel.open();

        SocketAddress socketAddress =
                new InetSocketAddress("localhost", 8080);

        ServerSocketChannel socketChannel = serverSocketChannel.bind(socketAddress);
        SocketChannel channel = socketChannel.accept();

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);

        ByteBuffer serverByteBuffer = ByteBuffer.allocateDirect(1024);

        while (true) {
            byteBuffer.clear();

            channel.read(byteBuffer);
            byte[] bytes = new byte[byteBuffer.position()];
            for (int i = 0; i < byteBuffer.position(); i++) {
                bytes[i] = byteBuffer.get(i);
            }

            System.out.println(new String(bytes));

            serverByteBuffer.clear();
            Scanner scanner = new Scanner(System.in);
            serverByteBuffer.put(scanner.next().getBytes());
            serverByteBuffer.flip();
            channel.write(serverByteBuffer);

        }
    }

}
