package com.wangwei.basic.alg;

import java.util.LinkedList;

public class DupArrays {

    public int removeDuplicates(int[] nums) {
        LinkedList<Integer> linkedList = new LinkedList<>();


        for (int i = 0; i < nums.length; i++) {
            int currentInt = nums[i];
            if (!Integer.valueOf(currentInt).equals(linkedList.peekLast())) {
                linkedList.addLast(currentInt);
                nums[linkedList.size() - 1] = nums[i];
            }
        }

        return linkedList.size();
    }

    public int removeElement(int[] nums, int val) {
        int j = 0;

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] != val) {
                nums[j] = nums[i];
                j++;
            }
        }

        return j;
    }

    public static void main(String[] args) {
        DupArrays dupArrays = new DupArrays();
        System.out.println(dupArrays.removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4}));

    }

}
