package com.wangwei.forward.es;

import org.elasticsearch.action.index.IndexResponse;

import java.io.IOException;

public class EsTest {

    public static void main(String[] args) throws IOException {
        IndexResponse index = MyEsClient.createIndex("3");

        System.out.println(index);
    }

}
