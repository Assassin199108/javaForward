package com.wangwei.basic.classload;

import com.wangwei.basic.algorithm.Sort;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyClassLoader extends ClassLoader {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://webuy-devmysql-nei.mysql.rds.aliyuncs.com:3306/sesame?useUnicode=true&amp;characterEncoding=UTF-8&amp;zeroDateTimeBehavior=convertToNull", "sesame", "_qIiCIESVnDFQ");
        System.out.println(Driver.class.getClassLoader());
        System.out.println(com.mysql.jdbc.Driver.class.getClassLoader());

        System.out.println(DriverManager.class.getClassLoader());
        System.out.println(connection.getClass().getClassLoader());

        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                InputStream stream = getClass().getResourceAsStream(fileName);
                if (stream == null) {
                    return super.loadClass(name);
                }
                try {
                    byte[] b = new byte[stream.available()];
                    // 将流写入字节数组b中
                    stream.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return super.loadClass(name);
            }
        };
        Object o = classLoader.loadClass("com.wangwei.basic.algorithm.Sort");

        Sort sort = new Sort();
        // print classloader
        System.out.println(sort.getClass().getClassLoader().toString());
        System.out.println(sort.getClass().getClassLoader().getParent().toString());

        if (o instanceof Sort) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }

    }

}
