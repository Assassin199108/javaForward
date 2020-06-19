package com.wangwei.basic.map;

public class HashTest {

    static class InnerClass {
        private String name = "wang";

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        InnerClass innerClass = new HashTest.InnerClass();

        System.out.println(innerClass.hashCode());

        System.out.println(System.identityHashCode(innerClass));
    }

}
