package com.wangwei.forward.es;

import lombok.Data;

import java.util.Map;

/**
 * es的字段
 *
 * @author wangwei
 */
@Data
public class Doc {

    /**
     * 索引
     */
    private String _index;

    /**
     * 类型
     */
    private String _type;

    /**
     * id
     */
    private String _id;

    /**
     * 字段值
     */
    private Map<String, String> _source;

}
