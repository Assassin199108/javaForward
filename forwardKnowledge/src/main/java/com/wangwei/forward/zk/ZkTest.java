package com.wangwei.forward.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.zookeeper.CreateMode;
import sun.misc.Cleaner;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ZkTest {

    private static final CuratorFramework client;

    static {
        client = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2181")
                .connectionTimeoutMs(30000)
                .sessionTimeoutMs(30000)
                .build();
    }

    public static void main(String[] args) {


    }

    /**
     * 创建临时节点
     *
     * @param path
     * @param data
     * @throws Exception
     */
    public static void createEphemeral(String path, byte[] data) throws Exception {
        client.create().withMode(CreateMode.EPHEMERAL).forPath(path, data);
    }

    /**
     * 创建节点
     *
     * @param path
     * @param data
     * @throws Exception
     */
    public static void create(String path, final byte[] data) throws Exception {
        client.create().creatingParentsIfNeeded().forPath(path, data);
    }

    /**
     * 创建临时顺序节点
     *
     * @param path
     * @param data
     * @throws Exception
     */
    public static void createEphemeralSequential(String path, final byte[] data) throws Exception {
        client.create().withProtection().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(path, data);
    }

    /**
     * 设置值
     *
     * @param path
     * @param data
     * @throws Exception
     */
    public static void setData(String path, final byte[] data) throws Exception {
        client.setData().forPath(path, data);
    }

    public static void delete(String path) throws Exception {
        client.delete().deletingChildrenIfNeeded().forPath(path);
    }

    public static void GuaranteedDelete(String path) throws Exception {
        client.delete().guaranteed().forPath(path);
    }

    public static String getData(String path) throws Exception {
        return new String(client.getData().forPath(path));
    }

    public static List<String> getChildren(String path) throws Exception {
        return client.getChildren().forPath(path);
    }

    public boolean tryLock(final String path) throws Exception {
        InterProcessMutex interProcessMutex = new InterProcessMutex(client, path);

        return interProcessMutex.acquire(1000, TimeUnit.SECONDS);
    }

}
