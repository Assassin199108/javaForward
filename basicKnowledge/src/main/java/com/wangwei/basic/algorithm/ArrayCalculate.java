package com.wangwei.basic.algorithm;


import java.util.*;

public class ArrayCalculate {

    /**
     * 面试题53 - II. 0～n-1中缺失的数字
     *
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        int sum = nums.length;

        int startP = -1;
        int endP = sum;

        while (++startP <= --endP) {
            if (nums[startP] + nums[endP] == sum) {
                continue;
            }

            if (nums[startP] + nums[endP] > sum) {
                return --nums[startP];
            } else {
                return ++nums[endP];
            }
        }

        return sum >> 1;
    }

    public int minArray(int[] numbers) {
        int left = 0, right = numbers.length - 1;

        while (left < right) {
            int mid = (left + right) / 2;
            if (numbers[mid] > numbers[right]) {
                left = mid + 1;
            } else if (numbers[mid] < numbers[right]) {
                right = mid;
            } else {
                right--;
            }
        }

        return numbers[left];
    }

    public int findRepeatNumber(int[] nums) {
        int[] numsRepeat = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numsRepeat[nums[i]]++;
            if (numsRepeat[nums[i]] > 1) {
                return nums[i];
            }
        }
        return 0;
        /*Map<Integer, Integer> repeatMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (repeatMap.getOrDefault(nums[i], 0) > 0) {
                return nums[i];
            }

            repeatMap.put(nums[i], 1);
        }

        return 0;*/
    }

    /**
     * 面试题39. 数组中出现次数超过一半的数字
     * 投票方法
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int votes = 0, x = 0;
        for (int num : nums) {
            if (votes == 0) x = num;
            votes += x == num ? 1 : -1;
        }

        return x;
    }

    public int[] printNumbers(int n) {
        if (n == 0) {
            return new int[0];
        }
        int[] reIs = new int[(int) Math.pow(10, n) - 1];

        for (int i = 1; i <= reIs.length; i++) {
            reIs[i - 1] = i;
        }

        return reIs;
    }

    public boolean isStraight(int[] nums) {
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE, zeroCount = 0;
        int[] repeatNums = new int[15];

        for (int num : nums) {
            if (num == 0) {
                zeroCount++;
                continue;
            }
            if (repeatNums[num]++ > 0) {
                return false;
            }
            if (num > max) {
                max = num;
            }
            if (num < min) {
                min = num;
            }
        }

        return max - min >= 4 - zeroCount && max - min <= 4 + zeroCount;
    }

    /**
     * 999. 车的可用捕获量
     * 4个for循环替换为4个方向的矢量
     *
     * @param board
     * @return
     */
    public int numRookCaptures(char[][] board) {
        int rRow = 0;
        int rCol = 0;
        int max = board.length;

        for (int i = 0; i < board.length - 1; i++) {
            for (int j = 0; j < board[i].length - 1; j++) {
                if (board[i][j] == 'R') {
                    rRow = i;
                    rCol = j;
                    break;
                }
            }
        }

        // 4个for循环 改为4个数组向量指标
        int sum = 0;
        int[][] vectors = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        for (int i = 0; i < vectors.length; i++) {
            int[] vector = vectors[i];
            int x = rRow;
            int y = rCol;

            while (x >= 0 && x < max && y >= 0 && y < max) {
                if (board[x][y] == 'B') {
                    break;
                }

                if (board[x][y] == 'p') {
                    sum++;
                    break;
                }

                x += vector[0];
                y += vector[1];
            }
        }

        return sum;
    }

    /**
     * 面试题53 - I. 在排序数组中查找数字 I
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if (nums.length < 1) {
            return 0;
        }
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                left = mid;
                break;
            } else if (nums[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        int start = left;
        int end = left + 1;
        int sum = 0;
        while (start >= 0) {
            if (nums[start--] != target) {
                break;
            }
            sum++;
        }
        while (end < nums.length) {
            if (nums[end++] != target) {
                break;
            }
            sum++;
        }

        return sum;
    }

    /**
     * 面试题62. 圆圈中最后剩下的数字
     *
     * @param n
     * @param m
     * @return
     */
    public int lastRemaining(int n, int m) {
        if(n==0||m==0)
            return -1;

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }

        int c = (m - 1) % n;
        while (list.size() > 1) {
            list.remove(c);
            c = (c+m-1) % list.size();
        }

        return list.get(0);

        /*int[] reI = new int[n];
        for (int i = 0; i < n; i++) {
            reI[i] = i;
        }

        int position = -1;
        int replaceCount = 0;
        int reInt = -1;
        while (replaceCount != n) {
            int loop = 0;
            while (loop < m) {
                position++;
                position = position >= n ? position - n : position;
                if (reI[position] < 0) {
                    continue;
                }
                loop++;
            }

            reInt = reI[position];
            reI[position] = -1;
            replaceCount++;
        }

        return reInt;*/
    }

    /**
     * 面试题57 - II. 和为s的连续正数序列
     * 现在解题为双指针
     * 可以使用滑动窗口
     * int i = 1; // 滑动窗口的左边界
     * int j = 1; // 滑动窗口的右边界
     * int sum = 0; // 滑动窗口中数字的和
     *
     * @param target
     * @return
     */
    public int[][] findContinuousSequence(int target) {
        int leftP = 1;
        int rightP = 2;
        int targetCount = 0;
        int[][] reI = new int[0][];
        while (leftP < target) {
            int sum = leftP;
            int size = 1;
            while (rightP < target && sum < target) {
                sum+= rightP++;
                size++;
            }

            if (sum == target) {
                reI = Arrays.copyOf(reI, ++targetCount);
                int[] targetInts = new int[size];
                int arrayCount = 0;
                for (int i = leftP; i < rightP; i++) targetInts[arrayCount++] = i;

                reI[targetCount - 1] = targetInts;
            }
            leftP++;
            rightP = leftP + 1;
        }

        return reI;
    }

    public int[] twoSum(int[] nums, int target) {
        int startP,endP;
        startP = 0;
        endP = nums.length - 1;

        int sum = nums[startP] + nums[endP];
        int rightP = endP;
        while (startP < nums.length - 1) {
            while (rightP < nums.length - 1 && sum < target)
                sum = nums[startP] + nums[++rightP];
            while (rightP - 1 > startP && sum > target)
                sum = nums[startP] + nums[--rightP];

            if (sum == target) {
                return new int[]{nums[startP], nums[rightP]};
            } else if (sum > target) {
                startP--;
                rightP = endP;
            } else {
                startP++;
                rightP = endP;
            }
            sum = nums[startP] + nums[endP];
        }

        return new int[0];
    }

    public boolean hasGroupsSizeX(int[] deck) {
        int[] ints = new int[1000];
        for (int i = 0; i < deck.length; i++) {
            ints[deck[i]]++;
        }

        int x = 0;
        for (int anInt : ints) {
            if (anInt > 0) {
                x = gcd(x, anInt);
                if (x == 1) {
                    return false;
                }
            }
        }

        return true;
    }

    // 辗转相除法
    private int gcd (int a, int b) {
        return b == 0? a: gcd(b, a % b);
    }

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length <= 0 || matrix[0].length <= 0) {
            return false;
        }
        int col = matrix[0].length - 1;

        int row = 0;
        int rowMax = matrix.length;

        while (row < rowMax && col >= 0) {
            int num = matrix[row][col];
            if (num == target) {
                return true;
            } else if (num > target) {
                col--;
            } else {
                row++;
            }
        }

        return false;
    }

    public void gameOfLife(int[][] board) {
        int rowLength = board.length;
        int colLength = board[0].length;

        int[][] copyBord = new int[rowLength][colLength];

        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                copyBord[i][j] = board[i][j];
            }
        }

        int[][] vectors = new int[][]{
                {-1, -1}, {-1, 0}, {-1, 1}, {0, -1},
                {0, 1},   {1, -1}, {1, 0},  {1, 1}};


        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                int aroundSum = 0;

                for (int[] vector : vectors) {
                    int rowVector = vector[0];
                    int colVector = vector[1];

                    if (i + rowVector < 0
                            || i + rowVector >= rowLength) {
                        continue;
                    }
                    if (j + colVector < 0
                            || j + colVector >= colLength) {
                        continue;
                    }

                    aroundSum += board[i + rowVector][j + colVector];
                }

                // 活细胞
                if (board[i][j] == 1) {
                    copyBord[i][j] = aroundSum >= 2 && aroundSum <= 3 ? 1 : 0;
                } else {
                    // 死细胞
                    copyBord[i][j] = aroundSum == 3 ? 1 : 0;
                }
            }
        }

        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                board[i][j] = copyBord[i][j];
            }
        }
    }

    public int robotSim(int[] commands, int[][] obstacles) {
        int rowPosition = 0;
        int colPosition = 0;
        int maxDistance = 0;

        int step = 0;
        int[][] vectors = new int[][]{
                {1, 0},
                {0, -1},
                {-1, 0},
                {0, 1}};

        Set<String> obstacleSet = new HashSet<>();
        for (int[] obstacle : obstacles) {
            obstacleSet.add(obstacle[1] + ":" + obstacle[0]);
        }

        for (int command : commands) {
            if (command < 0) {
                step = command == -2 ? (step + 1) % 4 : step - 1;
                step = step < 0 ? step + 4 : step;
                continue;
            }

            int rowStep = vectors[step][0];
            int colStep = vectors[step][1];

            for (int i = 1; i <= command; i++) {
                int nextRowP = rowPosition + rowStep;
                int nextColP = colPosition + colStep;
                if (obstacleSet.contains(nextRowP + ":" + nextColP)) {
                    break;
                }

                rowPosition = nextRowP;
                colPosition = nextColP;

                maxDistance = Math.max(
                        maxDistance,
                        (rowPosition*rowPosition) + (colPosition*colPosition));
            }
        }

        return maxDistance;
    }

    public int massage(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }

        return dp[nums.length - 1];
    }

    /**
     * 42. 接雨水
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        if (height.length <= 1) {
            return 0;
        }
        int n = height.length - 1;
        int slowP = 0;
        int fastP = 1;
        int sum = 0;

        while (slowP < n) {
            // 左指针小于右指针
            while (slowP < n - 1 && height[slowP] <= height[fastP]) {
                slowP++;
                fastP = slowP + 1;
            }

            // 左指针确定
            int slowHeight = height[slowP];
            int maxP = fastP;

            // 往下确定右指针
            while (fastP < n
                    && height[fastP] < slowHeight) {
                fastP++;
                maxP = height[fastP] >= height[maxP] ? fastP : maxP;
            }

            // 相当于慢指针后面的数永远比慢指针小
            if (fastP == n) {
                fastP = maxP;
            }

            int heightMin = Math.min(slowHeight, height[fastP]);

            for (int i = slowP + 1; i < fastP; i++) {
                sum += Math.max(heightMin - height[i], 0);
            }

            slowP = fastP;
            fastP = slowP + 1;
        }

        return sum;
    }

    /**
     * 种花问题
     *
     * @param flowerbed
     * @param n
     * @return
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int length = flowerbed.length;
        int zeroP = 0;

        int plantint = 0;
        for (int i = 0; i < length; i++) {
            boolean skip = false;
            if (flowerbed[i] == 1) {
                skip = true;
            }

            if (i - zeroP >= 2 && skip) {
                int divide = i - zeroP;
                plantint += (divide + (divide % 2)) / 2 - 1;
            }

            if (skip) {
                zeroP = i + 1;
            }
        }

        return plantint >= n;
    }

    public static void main(String[] args) {
        ArrayCalculate arrayCalculate = new ArrayCalculate();

        System.out.println(arrayCalculate.missingNumber(new int[]{0, 2}));

        System.out.println(arrayCalculate.minArray(new int[]{1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1}));

        System.out.println(arrayCalculate.findRepeatNumber(new int[]{2, 3, 1, 0, 2, 5, 3}));

        System.out.println(arrayCalculate.majorityElement(new int[]{2, 3, 2, 3, 2, 2, 3}));

        arrayCalculate.printNumbers(1);

        System.out.println(arrayCalculate.isStraight(new int[]{0, 0, 2, 2, 5}));

        char[][] board = {
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', 'p', '.', '.', '.', '.'},
                {'.', '.', '.', 'R', '.', '.', '.', 'p'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', 'p', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'}};

        System.out.println(arrayCalculate.numRookCaptures(board));

        System.out.println(arrayCalculate.lastRemaining(5, 3));

        arrayCalculate.findContinuousSequence(9);

        arrayCalculate.twoSum(new int[]{14,15,16,22,53,60}, 76);

        System.out.println(arrayCalculate.hasGroupsSizeX(new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,2,3,3,3,3,3,4,4,4,4,4,5,5,5,5,5,3,3}));

        int[][] matrix = new int[][] {
                {1,   4,  7, 11, 15},
                {2,   5,  8, 12, 19},
                {3,   6,  9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };

        int[][] matrix2 = new int[][] {
                {3, 3, 8, 13,13,18},
                {4, 5, 11,13,18,20},
                {9, 9, 14,15,23,23},
                {13,18,22,22,25,27},
                {18,22,23,28,30,33},
                {21,25,28,30,35,35},
                {24,25,33,36,37,40}
        };
        System.out.println(arrayCalculate.findNumberIn2DArray(matrix, 20));


        int[][] bord = new int[][] {
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        };
        arrayCalculate.gameOfLife(bord);

        System.out.println(arrayCalculate.robotSim(new int[]{4, -1, 4, -2, 4}, new int[][]{
                {2, 4}
        }));

        System.out.println(arrayCalculate.massage(new int[]{2,7,9,3,1}));

        System.out.println(arrayCalculate.trap(new int[]{1,7,8}));

        System.out.println(arrayCalculate.canPlaceFlowers(new int[]{0,0,1,0,1}, 2));
    }
}
