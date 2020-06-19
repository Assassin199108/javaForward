package com.wangwei.basic.io;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.time.Duration;
import java.time.Instant;

/**
 * 文件地址转换
 *
 * @author wangwei
 */
public class SimpleFileTransfer {

    public static void transferWithBlockingIO(String origin, String target) throws IOException {
        Instant now = Instant.now();

        File originFile = new File(origin);
        File targetFile = new File(target);

        FileInputStream inputStream = new FileInputStream(originFile);
        FileOutputStream outputStream = new FileOutputStream(targetFile);

        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);

        byte[] bytes = new byte[1024];
        int len;
        while ( (len = bufferedInputStream.read(bytes)) != -1) {
            bufferedOutputStream.write(bytes, 0, len);
        }

        bufferedInputStream.close();
        bufferedOutputStream.close();

        inputStream.close();
        outputStream.close();

        System.out.println("BIO耗费时间：" + Duration.between(now, Instant.now()).getSeconds());
    }

    public static void transferWithNonBlockingIO(String origin, String target) throws IOException {
        Instant now = Instant.now();

        File originFile = new File(origin);
        File targetFile = new File(target);

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);

        RandomAccessFile read = new RandomAccessFile(originFile, "rw");
        RandomAccessFile write = new RandomAccessFile(targetFile, "rw");

        FileChannel readChannel = read.getChannel();
        FileChannel writeChannel = write.getChannel();

        while (readChannel.read(byteBuffer) > 0) {
            byteBuffer.flip();
            writeChannel.write(byteBuffer);
            byteBuffer.clear();
        }

        readChannel.close();
        writeChannel.close();

        read.close();
        write.close();

        System.out.println("NIO耗费时间：" + Duration.between(now, Instant.now()).getSeconds());
    }

    public static void main(String[] args) throws IOException {
        transferWithBlockingIO("/Users/wangwei/Downloads/banner.txt", "/Users/wangwei/Desktop/bioCopy.txt");

        transferWithNonBlockingIO("/Users/wangwei/Downloads/banner.txt", "/Users/wangwei/Desktop/nioCopy.txt");
    }
    
}
