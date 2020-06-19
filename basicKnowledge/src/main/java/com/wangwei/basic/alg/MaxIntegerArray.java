package com.wangwei.basic.alg;

public class MaxIntegerArray {

    public static int maxSubArray(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int max = 0;
        int r = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (max >= 0) {
                max = nums[i] + max;
            } else {
                max = nums[i];
            }

            r =  Integer.max(r, max);
        }

        return r;
    }

    public static void main(String[] args) {
        int i = MaxIntegerArray.maxSubArray(new int[]{1});
        System.out.println(i);
    }

}
