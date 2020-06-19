package com.wangwei.basic.io;

import com.lmax.disruptor.RingBuffer;
import it.unimi.dsi.fastutil.longs.Long2IntOpenHashMap;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 数据传输服务层到应用层
 *
 * @author wangwei
 */
public class TransferDataServerToClient {

    public static void main(String[] args) throws IOException {
        BigFileReader reader = new BigFileReader(
                "",
                16 * 1024 * 1024 + 128 * 1024);

        reader.read();
        byte[] bytes = reader.getBytes();
        System.out.println(new String(bytes, StandardCharsets.UTF_8));
    }

}
