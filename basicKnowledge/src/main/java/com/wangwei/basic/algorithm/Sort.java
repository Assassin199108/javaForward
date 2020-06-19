package com.wangwei.basic.algorithm;

import java.util.Arrays;

public class Sort {

    /**
     * 面试题40. 最小的k个数
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        if (arr.length <= 0 || k == 0) {
            return new int[0];
        }
        // 内部实现好像就是快排
        // 自实现快排
        int left = 0;
        int right = arr.length - 1;

        return quickSortLeastNum(arr, left, right, k - 1);
    }

    public int[] quickSortLeastNum(int[] arr, int start, int end, int k) {
        int j = segmentQuickSort(arr, start, end);

        if (j == k) {
            return Arrays.copyOf(arr, j + 1);
        }

        return j < k
                ? quickSortLeastNum(arr, j + 1, end, k)
                : quickSortLeastNum(arr, start, j - 1, k);
    }

    public int segmentQuickSort(int[] arr, int start, int end) {
        int left = start;
        int right = end;
        int temp = arr[left];

        while (left < right) {
            while (left < right && temp <= arr[right]) {
                right--;
            }

            arr[left] = arr[right];

            while (left < right && temp >= arr[left]) {
                left++;
            }
            arr[right] = arr[left];
        }

        arr[left] = temp;
        return left;
    }

    /**
     * 传说中的快排
     * 最优选
     * 时间复杂度: o(nlogn)
     * 空间复杂度: o(nlogn)
     * 不稳定
     *
     * 速度最快
     * 不稳定，不适合对象排序
     * 空间复杂度比堆排高
     *
     * @param a
     * @param start
     * @param end
     */
    public void quickSort(int[] a, int start, int end) {
        if (a.length < 0) {
            return;
        }
        if (start >= end) {
            return;
        }
        int left = start;
        int right = end;
        int temp = a[left];
        while (left < right) {
            while (left < right && temp < a[right]) {
                right--;
            }

            a[left] = a[right];

            while (right > left && temp > a[left]) {
                left++;
            }

            a[right] = a[left];
        }

        a[left] = temp;
        quickSort(a, start, left - 1);
        quickSort(a, right + 1, end);
    }

    /**
     * 堆排
     * 时间复杂度: o(nlogn)
     * 空间复杂度: o(1)
     * 不稳定
     *
     * 堆排对于经常变动的数据难以胜任
     * 更新后整个堆的重新排序
     * 维护性差
     *
     *
     * @param arr
     * @return
     */
    public int[] heapSort(int[] arr) {
        this.heapSortBuild(arr);

        this.exchangeHeapSort(arr);

        return arr;
    }

    /**
     * 建立二叉堆
     * begin:[20,30,90,40,70,110,60,10,100,50,80]
     * tree:            20
     *             /        \
     *            30         90
     *         /     \     /   \
     *       40      70   110  60
     *       / \     / \
     *      10 100  50 80
     * build:                 110
     *                   /         \
     *                 100         90
     *               /      \     /   \
     *              40      80   20  60
     *             / \     /  \
     *            10 30  50   70
     *
     * @param arr 数组
     */
    public void heapSortBuild(int[] arr) {
        int maxLength = arr.length;
        // 叶子节点
        int leafNode = maxLength / 2 - 1;

        // 从(n/2-1) --> 0逐次遍历。遍历之后，得到的数组实际上是一个(最大)二叉堆。
        for (int i = leafNode; i >= 0; i--) {
            this.heapSortBuildCompare(arr, i, arr.length);
        }
    }

    /**
     * 交换堆数据
     * 从小到大排序
     * 首先第0个与最后一个交换，那么最后一个就是最大值
     * 重新排序下
     * 调整a[0...i-1]，使得a[0...i-1]仍然是一个最大堆
     * 即，保证a[i-1]是a[0...i-1]中的最大值。
     * 循环替换a0 <-> a[i-1]
     *
     * @param arr  数组
     */
    public void exchangeHeapSort(int[] arr) {
        // 从最后一个元素开始对序列进行调整，不断的缩小调整的范围直到第一个元素
        for (int i = arr.length - 1; i >= 0; i--) {
            int tmp = arr[0];
            int c = arr[i];
            // 交换a[0]和a[i]。交换后，a[i]是a[0...i]中最大的。
            arr[i] = tmp;
            arr[0] = c;

            this.heapSortBuildCompare(arr, 0, i - 1);
        }
    }

    /**
     * (最大)堆的向下调整算法
     * <p>
     * 注：数组实现的堆中，第N个节点的左孩子的索引值是(2N+1)，右孩子的索引是(2N+2)。
     * 其中，N为数组下标索引值，如数组中第1个数对应的N为0。
     * <p>
     * 参数说明：
     * a -- 待排序的数组
     * start -- 被下调节点的起始位置(一般为0，表示从第1个开始)
     * end   -- 截至范围(一般为数组中最后一个元素的索引)
     */
    public void heapSortBuildCompare(int[] arr, int leafNode, int end) {
        int c = leafNode * 2 + 1;
        int tmp = arr[leafNode];

        for (; c < end; leafNode = c, c = c * 2 + 1) {
            if (c < end - 1 && arr[c] < arr[c + 1])
                c++;
            if (tmp > arr[c])
                break;
            else {
                arr[leafNode] = arr[c];
                arr[c] = tmp;
            }
        }
    }

    public int[] sortArray(int[] nums) {

        this.quickSort2(nums, 0, nums.length - 1);

        return nums;
    }

    /**
     * 快排练习
     *
     * @param nums
     * @param start
     * @param end
     */
    public void quickSort2(int[] nums, int start, int end) {
        if (nums.length <= 0) {
            return;
        }

        if (start >= end) {
            return;
        }

        int lefP = start;
        int rightP = end;
        int temp = nums[start];
        while (lefP < rightP) {
            while (lefP < rightP && temp < nums[rightP]) rightP--;

            nums[lefP] = nums[rightP];

            while (rightP > lefP && nums[lefP] <= temp) lefP++;

            nums[rightP] = nums[lefP];
        }

        nums[lefP] = temp;

        quickSort2(nums, start, lefP - 1);
        quickSort2(nums, lefP + 1, end);
    }

    /**
     * 二叉堆建立练习
     *
     * @param nums
     * @param leafNode
     * @param length
     */
    public void heapSort2(int[] nums, int leafNode, int length) {
        int childNode = 2 * leafNode + 1;
        int temp = nums[leafNode];

        while (childNode <= length) {

            if (childNode < length && nums[childNode] < nums[childNode + 1])
                childNode++;

            if (temp >= nums[childNode]) {
                break;
            }
            nums[leafNode] = nums[childNode];

            leafNode = childNode;
            childNode = 2 * childNode + 1;
        }

        nums[leafNode] = temp;
    }

    /**
     * 堆排练习
     *
     * @param nums
     */
    public void exchangeHeapSort2(int[] nums) {
        int leafNode = nums.length / 2 - 1;
        int length = nums.length - 1;

        for (int i = leafNode; i >= 0; i--) {
            this.heapSort2(nums, i, length);
        }

        while (length > 0) {
            int tmp = nums[length];
            nums[length] = nums[0];
            nums[0] = tmp;

            this.heapSort2(nums, 0, --length);
        }
    }

    /**
     * 冒泡排序练习
     * 比较简单,
     * 稳定的
     * 时间复杂度: o(n²)
     * 空间复杂度: o(1)
     *
     * @param nums
     */
    public void bubblingSort(int[] nums) {

        // 外层循环控制排序趟数
        for (int i = 0; i < nums.length; i++) {
            // 内层循环控制每一趟排序多少次
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int tmp = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = tmp;
                }
            }
        }

        for (int num : nums) {
            System.out.print(num + ",");
        }
    }

    /**
     * 选择排序
     * 不稳定
     * 时间复杂度: o(n²)
     * 空间复杂度: o(1)
     *
     *
     * @param nums
     */
    public void selectSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int min = nums[i];

            int loop = i + 1;
            int tmpNum = i;
            //寻找最小数
            while (loop < nums.length) {
                if (min <= nums[loop++]) {
                    continue;
                }
                //将最小数的索引赋值
                tmpNum = loop - 1;
                min = nums[tmpNum];
            }

            int tmp = nums[i];
            nums[i] = min;
            nums[tmpNum] = tmp;
        }

        System.out.println();

        for (int num : nums) {
            System.out.print(num + ",");
        }
    }

    /**
     * 插入排序
     * 时间复杂度: o(n²)
     * 空间复杂度: o(1)
     * 不稳定
     *
     * 小规模数据
     * 基本有序或十分有序
     * 效率很高
     * 否则使用希尔排序
     *
     * 从第二位数字开始，每一个数字都试图跟它的前一个比较并交换，并重复；
     * 直到前一个数字不存在或者比它小或相等时停下来
     *
     * @param nums
     */
    public void insertSort(int[] nums) {
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            int tmp = nums[i];

            int loop = i - 1;
            while (loop >= 0 && tmp < nums[loop]) {
                nums[loop + 1] = nums[loop];
                loop--;
            }

            nums[loop + 1] = tmp;
        }

        System.out.println();

        for (int num : nums) {
            System.out.print(num + ",");
        }
    }

    /**
     * 希尔排序
     * 时间复杂度: o(n¹·³)
     * 空间复杂度: o(1)
     * 不稳定
     *
     * 插入排序的升级版
     *
     * @param array
     */
    public void xiErSort(int[] array) {
        int n = array.length;

        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                this.xiErInsertSort(array, gap, i);
            }
        }
    }

    /**
     * 希尔插入排序
     *
     * @param array
     * @param gap
     * @param i
     */
    public void xiErInsertSort(int[] array, int gap, int i) {
        int tmp = array[i];

        int j;

        for (j = i - gap; j >= 0 && tmp < array[j] ; j -= gap) {
            array[j+gap] = array[j];
        }

        array[j+gap] = tmp;
    }

    /**
     * 归并排序
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(N)，归并排序需要一个与原数组相同长度的数组做辅助来排序
     * 稳定性：归并排序是稳定的排序算法
     *
     * 最接近快排时间复杂度的排序
     * 就是比较耗空间
     *
     * @param array
     */
    public void mergeSort(int[] array) {
        //在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
        int[] temps = new int[array.length];

        loopMerge(array, 0, temps.length - 1, temps);

        System.out.println();

        for (int i : array) {
            System.out.print(i + ",");
        }
    }

    /**
     * 循环分组
     * 经典的分治策略
     * 第1分  数组[0][1]
     * 第2分  数组[0][1][2]
     * 第3分  数组[3][4]
     * 第4分  数组[3][4][5]
     * ------------------------------
     * 第一次合并[0][1][2] -- [3][4][5]
     * 第5分  数组[0][1][2][3][4][5]
     * ------------------------------
     * 第6分  数组[6][7]
     * 第7分  数组[6][7][8]
     * 第8分  数组[9][10]
     * ------------------------------
     * 第二次合并[6][7][8] -- [9][10]
     * 第9分  数组[6][7][8][9][10]
     * ------------------------------
     * 第三次合并[0][1][2][3][4][5] --- [6][7][8][9][10]
     * 第10分 数组[0][1][2][3][4][5][6][7][8][9][10]
     * ------------------------------
     *
     * @param array 原数组
     * @param start 开始的数组位置
     * @param end   结束的数组位置
     * @param temps 临时数组
     */
    public void loopMerge(int[] array, int start, int end, int[] temps) {
        if (start >= end) {
            return;
        }

        int middle = (start + end) / 2;
        loopMerge(array, start, middle, temps);
        loopMerge(array, middle + 1, end, temps);
        compareAndSwap(array, start, end, middle, temps);
    }

    public void compareAndSwap(int[] array, int start, int end, int middle, int[] temps) {
        // 左指针
        int left = start;
        // 右指针
        int right = middle + 1;
        // 临时数组指针
        int t = 0;
        //左数组 右数组
        while (left <= middle && right <= end) {
            // 左数组的第一个 与 右数组的第一个进行比对
            if (array[left] <= array[right]) {
                // 左数组边的比较小 把左数组的数放置在临时数组中
                // 临时数组指针++ 左数组指针++
                temps[t++] = array[left++];
            } else {
                // 右数组边的比较小 把右数组的数放置在临时数组中
                // 临时数组指针++ 右数组指针++
                temps[t++] = array[right++];
            }
        }

        // 如果左数组还有剩余 把剩余的数添加到临时数组
        while (left <= middle) {
            temps[t++] = array[left++];
        }

        // 如果右数组还有剩余 把剩余的数添加到临时数组
        while (right <= end) {
            temps[t++] = array[right++];
        }

        // 重制临时数组指针
        t = 0;
        // 将临时数组的值赋值到原数组
        while (start <= end) {
            array[start++] = temps[t++];
        }
    }

    /**
     * 计数排序
     * 输入数组的元素都是介于0..k之间的
     * 计数排序要求输入的数据必须是有确定范围的整数
     *
     * 时间复杂度: o(n+k)
     * 空间复杂度: o(n+k)
     * 稳定
     *
     * 非比较排序
     *
     * @param array 待排序数组
     * @param k     最大元素
     */
    public void countSort(int[] array, int k) {
        // 存放临时数据的数组tmp，初始元素都是0；k为数组中最大元素
        int[] temps = new int[k + 1];

        for (int i = 0; i < array.length; i++) {
            temps[array[i]] ++;
        }

        for (int i = 1; i <= k; i++) {
            temps[i] = temps[i] + temps[i-1];
        }

        int[] result = new int[array.length];
        for (int i = array.length - 1; i >= 0 ; i--) {
            result[temps[array[i]] - 1] = array[i];
            temps[array[i]]--;
        }

        System.out.println();

        for (int i : result) {
            System.out.print(i + ",");
        }
    }

    /**
     * 桶排序
     * 计数排序的升级版
     *
     * 桶排序的要点: 分几个桶 桶内范围的公式(Max - Min) / 桶数 + 1
     *          : 数放入几号桶的公式 (数字 - Min) / 范围
     *          : 桶内数据插入排序
     *          : 桶内数据合并
     *
     * 时间复杂度: o(n+k)
     * 空间复杂度: o(n+k)
     * 稳定
     *
     * 桶排序是稳定的
     * 大多数情况下桶排序是常见排序里最快的一种,比快排还要快
     *
     * 非常浪费存储空间
     * 排序的数据非常限定，只能在整型范围之内
     *
     */
    public void bucketSort(int[] array) {
        // 11个数字分为3个桶
        // 获取最大值 最小值
        int bucketNum = 3;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        int tempPoint = 0;

        for (int i = 0; i < array.length; i++) {
            min = Math.min(min, array[i]);
            max =  Math.max(max, array[i]);
        }

        int range = (max - min) / bucketNum + 1;
        int[][] buckets = new int[bucketNum][0];

        // 数放入桶中
        for (int i = 0; i < array.length; i++) {
            // 几号桶
            int index = (array[i] - min) / range;
            buckets[index] = this.growCapacity(buckets[index], array[i]);
        }

        for (int i = 0; i < bucketNum; i++) {
            if (buckets[i].length > 0) {
                this.insertSort(buckets[i]);

                for (int sortedNum : buckets[i]) {
                    array[tempPoint++] = sortedNum;
                }
            }
        }

        System.out.println();

        for (int i : array) {
            System.out.print(i + ",");
        }
    }

    /**
     * 扩容&新增值
     *
     * @param array 原数组
     * @param num   新增数
     * @return      返回新数组
     */
    public int[] growCapacity(int[] array, int num) {
        array = Arrays.copyOf(array, array.length + 1);
        array[array.length - 1] = num;

        return array;
    }

    /**
     * 基数排序
     *
     * 要点:  数组内的最大值
     *      :当前的倍率 先取个位 后取十位 当倍率大于最大数 return 表示排好
     *      :建立10位数的二维数组 第二维是数据往上叠加
     *      :建立每个桶里保存数量的数组 记录有效的桶数据个数
     *      :再把桶里的全部数据塞回数组
     *      :清空桶计数 与 临时指针
     *
     * 空间复杂度:O(k*n)
     * 空间复杂度:O(n+k)
     * 稳定
     */
    public void baseSort(int[] array) {
        int max = Integer.MIN_VALUE;
        for (int num : array) {
            max = Math.max(max, num);
        }
        // 倍率 获取当前个位 十位 百位
        int n = 1;
        // 每次循环的临时指针
        int k = 0;
        int length = array.length;
        // 排序桶用于保存每次排序后的结果，这一位上排序结果相同的数字放在同一个桶里
        int[][] bucketNums = new int[10][length];
        // 用于保存每个桶里有多少个数字
        int[] bucketCountNums = new int[length];

        while (n < max) {

            // 将数组array里的每个数字放在相应的桶里
            for (int num : array) {
                int digit = (num / n) % 10;
                bucketNums[digit][bucketCountNums[digit]] = num;
                bucketCountNums[digit]++;
            }
            // 将前一个循环生成的桶里的数据覆盖到原数组中用于保存这一位的排序结果
            for (int i = 0; i < length; i++) {
                // 这个桶里有数据，从上到下遍历这个桶并将数据保存到原数组中
                if (bucketCountNums[i] != 0) {
                    for (int j = 0; j < bucketCountNums[i]; j++) {
                        array[k++] = bucketNums[i][j];
                    }
                }
                // 将桶里计数器置0，用于下一次位排序
                bucketCountNums[i] = 0;
            }

            n*=10;
            // 将k置0，用于下一轮保存位排序结果
            k=0;
        }
    }

    public static void main(String[] args) {
        Sort sort = new Sort();

        sort.getLeastNumbers(new int[]{0, 0, 1, 2, 4, 2, 2, 3, 1, 4}, 8);

        sort.heapSortBuild(new int[]{20, 30, 90, 40, 70, 110, 60, 10, 100, 50, 80});

        sort.heapSort(new int[]{20, 30, 90, 40, 70, 110, 60, 10, 100, 50, 80});

        sort.sortArray(new int[]{5, 2, 3, 1});

        sort.exchangeHeapSort2(new int[]{20, 30, 90, 40, 70, 110, 60, 10, 100, 50, 80});

        sort.bubblingSort(new int[]{20, 30, 90, 40, 70, 110, 60, 10, 100, 50, 80});

        sort.selectSort(new int[]{20, 30, 90, 40, 70, 110, 60, 10, 100, 50, 80});

        sort.insertSort(new int[]{20, 30, 90, 40, 70, 110, 60, 10, 100, 50, 80});

        sort.xiErSort(new int[]{20, 30, 90, 40, 70, 110, 60, 10, 100, 50, 80});

        sort.mergeSort(new int[]{20, 30, 90, 40, 70, 110, 60, 10, 100, 50, 80});

        sort.countSort(new int[]{13, 13, 90, 40, 70, 110, 60, 5, 100, 80}, 110);

        sort.bucketSort(new int[]{20, 30, 90, 40, 70, 110, 60, 10, 100, 50, 80});

        sort.baseSort(new int[]{20, 30, 90, 40, 70, 110, 60, 10, 100, 50, 80});
    }

}
