package com.wangwei.basic.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;

public class MyNioServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        SocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 8080);
        ServerSocketChannel socketChannel = serverSocketChannel.bind(socketAddress);

        SocketChannel channel = socketChannel.accept();

        while (true) {
            SelectorProvider selectorProvider = serverSocketChannel.provider();
            int select = selectorProvider.openSelector().select();

            switch (select) {
                case SelectionKey.OP_READ:
                    System.out.println("服务端读请求");
                    break;

                case SelectionKey.OP_ACCEPT:
                    System.out.println("服务端还未接收客户端连接");
                    break;

                case SelectionKey.OP_CONNECT:
                    System.out.println("服务端连接请求");
                    break;

                case SelectionKey.OP_WRITE:
                    System.out.println("服务端写请求");
                    break;
                default:
                    break;
            }
        }
    }

}
