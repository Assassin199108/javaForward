package com.wangwei.basic.map;

import javax.print.attribute.standard.PrinterStateReasons;
import javax.swing.*;
import java.awt.*;
import java.security.Provider;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.Attributes;

public class package_info {

    /**
     * 通用Map
     * 用于在应用程序中管理映射，通常在 java.util 程序包中实现
     */
    protected class generalMap{

        /**
         * 最常用的Map,它根据键的HashCode 值存储数据,根据键可以直接获取它的值，具有很快的访问速度。
         * HashMap最多只允许一条记录的键为Null(多条会覆盖);允许多条记录的值为 Null。非同步的
         *
         * @return HashMap
         */
        public HashMap hashMap(){
            return null;
        }

        /**
         * 与 HashMap类似,不同的是:key和value的值均不允许为null;它支持线程的同步，
         * 即任一时刻只有一个线程能写Hashtable,因此也导致了Hashtale在写入时会比较慢。
         *
         * @return Hashtable
         */
        public Hashtable hashtable(){
            return null;
        }

        /**
         * @return Properties
         */
        public Properties properties(){
            return null;
        }

        /**
         * 保存了记录的插入顺序，在用Iterator遍历LinkedHashMap时，
         * 先得到的记录肯定是先插入的.在遍历的时候会比HashMap慢。key和value均允许为空，非同步的。
         *
         * @return LinkedHashMap
         */
        public LinkedHashMap linkedHashMap(){
            return null;
        }

        /**
         * @return IdentityHashMap
         */
        public IdentityHashMap identityHashMap(){
            return null;
        }

        /**
         * 能够把它保存的记录根据键(key)排序,默认是按升序排序，也可以指定排序的比较器，
         * 当用Iterator 遍历TreeMap时，得到的记录是排过序的。TreeMap不允许key的值为null。非同步的。
         *
         * @return TreeMap
         */
        public TreeMap treeMap(){
            return null;
        }

        /**
         * @return WeakHashMap
         */
        public WeakHashMap weakHashMap(){
            return null;
        }

        /**
         * @return ConcurrentHashMap
         */
        public ConcurrentHashMap concurrentHashMap(){
            return null;
        }
    }

    /**
     * 专用Map
     * 通常我们不必亲自创建此类Map，而是通过某些其他类对其进行访问
     */
    protected class specialHashMap{

        /**
         * @return Attributes
         */
        public Attributes attributes(){
            return null;
        }

        /**
         * @return PrinterStateReasons
         */
        public PrinterStateReasons printerStateReasons(){
            return null;
        }

        /**
         * @return Provider
         */
        public Provider provider(){
            return null;
        }

        /**
         * @return RenderingHints
         */
        public RenderingHints renderingHints(){
            return null;
        }

        /**
         * @return UIDefaults
         */
        public UIDefaults uiDefaults(){
            return null;
        }
    }

    /**
     * 自行实现Map
     * 一个用于帮助我们实现自己的Map类的抽象类
     */
    protected class OwnerMap{

        /**
         * @return AbstractMap
         */
        public AbstractMap abstractMap(){
            return null;
        }

    }
}
