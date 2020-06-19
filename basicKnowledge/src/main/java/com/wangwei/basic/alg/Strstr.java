package com.wangwei.basic.alg;

public class Strstr {

    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }

        return haystack.indexOf(needle);
    }

    public static void main(String[] args) {
        Strstr strstr = new Strstr();
        System.out.println(strstr.strStr("hello", "ll"));

    }

}
