package com.wangwei.basic.map.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;

/**
 * 个人信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Comparator<Person>,Comparable<Person> {

    private String name;

    private Integer age;

    /**
     * 先比较年纪大小比较
     * 如果相同比较姓名
     *
     * @param o1 Person
     * @param o2 Person
     * @return int
     */
    @Override
    public int compare(Person o1, Person o2) {
        if (o1.getAge().compareTo(o2.getAge())==0){
            return o1.getName().compareTo(o2.getName());
        }

        return o1.getAge().compareTo(o2.getAge());
    }

    @Override
    public int compareTo(Person o) {
        if (this.getAge().compareTo(o.getAge()) == 0){
            return -this.getName().compareTo(o.getName());
        }

        return this.getAge().compareTo(o.getAge());
    }
}
