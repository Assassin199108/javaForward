package com.wangwei.basic.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 大文件读取
 *
 * @author wangwei
 */
public class BigFileReader {

    /**
     * 文件通道
     */
    private FileChannel fileChannel;
    /**
     * 文件流
     */
    private FileInputStream fis;
    /**
     * 文件映射对象数组
     */
    private MappedByteBuffer[] mappedByteBuffers;
    /**
     * 总共的数组大小
     */
    private int byteBufferCount;
    /**
     * 当前读取文件的下表号
     */
    private int byteBufferCountIndex;
    /**
     * 内存映射设置的buffer数值
     */
    private int byteBufferSize;
    /**
     * 当前读取的真正的大小
     */
    private int realSize;
    /**
     * 当前读取的字节映射
     */
    private byte[] bytes;


    public BigFileReader(String filePath, final int byteBufferSize) throws IOException {
        this.fis = new FileInputStream(filePath);

        this.fileChannel = fis.getChannel();
        long size = fileChannel.size();

        this.byteBufferCount = (int) Math.ceil((double) size / (double) byteBufferSize);
        this.mappedByteBuffers = new MappedByteBuffer[byteBufferCount];
        this.byteBufferSize = byteBufferSize;

        long preLength = 0;
        long regionSize = byteBufferSize;
        for (int i = 0; i < byteBufferCount; i++) {
            if (size - preLength < byteBufferSize) {
                regionSize = size - preLength;
            }

            mappedByteBuffers[i] =  fileChannel.map(FileChannel.MapMode.READ_ONLY, preLength, regionSize);

            preLength += regionSize;
        }
    }

    public int read() {
        if (byteBufferCountIndex >= byteBufferCount) {
            return -1;
        }

        int limit = this.mappedByteBuffers[byteBufferCountIndex].limit();
        int position = this.mappedByteBuffers[byteBufferCountIndex].position();

        realSize = byteBufferSize;
        if (limit - position < byteBufferSize) {
            realSize = limit - position;
        }

        bytes = new byte[realSize];
        mappedByteBuffers[byteBufferCountIndex].get(bytes);

        if (realSize < byteBufferSize && byteBufferCountIndex < byteBufferCount) {
            byteBufferCountIndex++;
        }

        return realSize;
    }

    public byte[] getBytes() {
        return this.bytes;
    }

    public void close() throws IOException {
        fileChannel.close();
        fis.close();
        bytes = null;
        for (MappedByteBuffer mappedByteBuffer : mappedByteBuffers) {
            mappedByteBuffer.clear();
        }
    }
}
