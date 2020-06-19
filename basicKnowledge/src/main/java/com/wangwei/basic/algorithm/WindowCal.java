package com.wangwei.basic.algorithm;


public class WindowCal {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || k == 1) {
            return nums;
        }

        int[] reI = new int[nums.length - k + 1];
        int position = -1, max = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length - k + 1; i++) {
            if (position >= i && position < i + k) {
                if (nums[i + k - 1] >= max) {
                    max = nums[i + k - 1];
                    position = i + k - 1;
                }
            } else {
              // 不在窗口内
                max = nums[i];
                for (int j = i; j < i + k; j++) {
                    if (max < nums[j]) {
                        max = nums[j];
                        position = j;
                    }
                }
            }

            reI[i] = max;
        }

        return reI;
    }

    public static void main(String[] args) {
        WindowCal windowCal = new WindowCal();
        windowCal.maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3);
    }

}
