package com.wangwei.forward.map;

import com.wangwei.forward.map.entity.Person;

import java.util.*;

/**
 * 有序树排序测试
 */
public class TreeMapTest {

    /**
     * Map Key排序
     */
    private static void sort(){
        Map<Person,String> sortedMap = new TreeMap<>(Person::compareTo);
        sortedMap.put(Person.builder().name("王五").age(20).build(),"王五");
        sortedMap.put(Person.builder().name("张三").age(10).build(),"张三");
        sortedMap.put(Person.builder().name("李四").age(10).build(),"李四");

        for (Map.Entry<Person, String> entry : sortedMap.entrySet()) {
            System.out.println(String.format("%d : %s",entry.getKey().getAge(),entry.getValue()));
        }
    }

    /**
     * 对Map的Value进行排序
     */
    protected class EntrySort implements Comparator<String>{

        private Map<String,Person> map;

        public EntrySort(Map<String, Person> map) {
            this.map = map;
        }

        @Override
        public int compare(String o1, String o2) {
            return map.get(o1).compareTo(map.get(o2));
        }
    }

    /**
     * Map value排序
     */
    private void sortMapValue(){
        Map<String,Person> map = new HashMap<>();
        EntrySort entrySort = new EntrySort(map);
        map.put("1",Person.builder().name("张三").age(10).build());
        map.put("2",Person.builder().name("李四").age(30).build());
        map.put("3",Person.builder().name("王五").age(20).build());
        Map<String,Person> treeMap = new TreeMap<>(entrySort);
        treeMap.putAll(map);

        for (Map.Entry<String, Person> entry : treeMap.entrySet()) {
            System.out.println(String.format("%d : %s",entry.getValue().getAge(),entry.getValue().getName()));
        }
    }

    public static void main(String[] args){
        sort();

        TreeMapTest treeMapTest = new TreeMapTest();
        treeMapTest.sortMapValue();
    }

}
