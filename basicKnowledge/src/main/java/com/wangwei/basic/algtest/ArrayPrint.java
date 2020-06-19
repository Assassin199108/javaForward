package com.wangwei.basic.algtest;

import java.util.ArrayList;
import java.util.List;

public class ArrayPrint {

    /**
     * 3:  实现函数,给定一个字符串数组,求该数组的连续非空子集，分別打印出来各子集 ，举例数组为[abc]，输出[a],[b],[c],[ab],[bc],[abc]
     *
     * @param args
     */
    public static void main(String[] args) {
        ArrayPrint print = new ArrayPrint();
        print.printArray("abc");
    }

    private String[] printArray(String s) {
        if (s.length() == 0) {
            return new String[0];
        }
        if (s.length() == 1) {
            return new String[]{s};
        }

        int length = s.length();

        int leftP = 0;
        int rightP = leftP + 1;

        List<String> list = new ArrayList<>();

        while (leftP < rightP && leftP < length) {
            String s1 = s.substring(leftP, rightP);

            System.out.println(s1);
            list.add(s1);
            rightP++;
            if (rightP > length) {
                leftP++;
                rightP = leftP + 1;
            }
        }

        String[] ss = new String[list.size()];
        return list.toArray(ss);
    }

}
