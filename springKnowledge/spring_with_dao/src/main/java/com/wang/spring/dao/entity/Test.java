package com.wang.spring.dao.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * (Test)实体类
 *
 * @author makejava
 * @since 2020-05-14 17:33:32
 */
@Data
@Builder
public class Test implements Serializable {
    private static final long serialVersionUID = 883533018189399557L;
    /**
    * 主键
    */
    private Long id;
    /**
    * 名称
    */
    private String name;
    /**
    * 年龄
    */
    private Integer age;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}