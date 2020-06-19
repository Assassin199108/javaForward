package com.wangwei.basic.alg;

import javafx.util.Pair;

import java.math.BigInteger;
import java.util.*;

public class Solution {

    public int  searchInsert(int[] nums, int target) {
        int lowest = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            if (lowest <= target && target <= nums[i]) {
                return i;
            }
        }

        return nums.length;
    }

    public String countAndSay(int n) {
        String s = "1";

        int countWhile = 1;
        while (n > countWhile) {
            StringBuilder sb = new StringBuilder();
            Map<String, Integer> map = new HashMap<>();

            boolean first = true;

            for (int i = 0; i < s.length(); i++) {
                String currentChar = s.substring(i, i + 1);

                Integer count = map.get(currentChar);
                if (first) {
                    count = 0;
                }
                if (count == null) {

                    String lastString = s.substring(i - 1, i);
                    sb.append(map.get(lastString))
                            .append(lastString);

                    map.remove(lastString);

                    count = 0;
                }

                map.put(currentChar, count + 1);
                first = false;
            }

            String lastString = s.substring(s.length() - 1);
            sb.append(map.get(lastString)).append(lastString);

            s = sb.toString();
            countWhile++;
        }

        return s;
    }

    public static int lengthOfLastWord(String s) {
        int count = 0;
        s = s.trim();
        for (int i = s.length(); i > 0; i--) {
            if (s.charAt(i - 1) != ' ') {count ++;}
            else{ break;}
        }

        return count;
    }

    public static int[] plusOne(int[] digits) {

        for (int i = digits.length - 1; i >= 0 ; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0) return digits;
        }

        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    public static String addBirary(String a, String b) {
        BigInteger abi = new BigInteger(a, 2);
        BigInteger bbi = new BigInteger(b, 2);

        BigInteger forward = new BigInteger("0", 2);
        BigInteger carry, answer;

        while (bbi.compareTo(forward) != 0) {
            answer = abi.xor(bbi);
            carry = abi.and(bbi).shiftLeft(1);

            abi = answer;
            bbi = carry;
        }

        return abi.toString(2);
    }

    public static int climbStairs(int n) {
        return climbStairsTwoWays(0, n);
    }

    public static int climbStairsTwoWays(int step, int n) {
        if (step > n) {
            return 0;
        }

        if (step == n) {
            return 1;
        }

        return climbStairsTwoWays(step+1, n) + climbStairsTwoWays(step+2, n);
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        int headVal = head.val;
        ListNode listNode = new ListNode(0);
        ListNode cur = listNode;

        while (head != null) {
            if (cur.val != head.val) {
                cur.next = new ListNode(head.val);
                cur = cur.next;
            }

            head = head.next;
        }

        if (headVal == 0) {
            return listNode;
        }

        return listNode.next;
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p = --m + --n + 1;
        while (m >= 0 && n >= 0) {
            if (nums1[m] > nums2[n]) {
                nums1[p] = nums1[m];
                m--;
            } else {
                nums1[p] = nums2[n];
                n--;
            }

            p--;
        }

        while (n >= 0) {
            nums1[p] = nums2[n];
            p--;
            n--;
        }
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if (p == null || q == null) return false;
        if (p.val != q.val) return false;

        return isSameTree(p.left, q.right) && isSameTree(p.right, q.left);
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.right == null && root.left == null) {
            return true;
        } else if (root.right == null || root.left == null) {
            return false;
        }

        return this.isSameTree(root.left, root.right);
    }

    public int compareMaxDepth(TreeNode left, TreeNode right, int leftCount, int rightCount) {
        if (left == null && right == null) {
            return Integer.max(leftCount, rightCount);
        }

        if (left != null && right != null) {
            leftCount++;
            rightCount++;
            return Integer.max(
                    compareMaxDepth(left.left, left.right, leftCount, rightCount),
                    compareMaxDepth(right.left, right.right, leftCount, rightCount));
        } else if (left == null) {
            rightCount++;
            return compareMaxDepth(right.left, right.right, rightCount, rightCount);
        } else {
            leftCount++;
            return compareMaxDepth(left.left, left.right, leftCount, leftCount);
        }
    }

    /**
     * 二叉树的最大深度
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        return compareMaxDepth(root.left, root.right, 1, 1);
    }

    /**
     *  将数组分成和相等的三个部分
     *
     * @param A
     * @return
     */
    public boolean canThreePartsEqualSum(int[] A) {
        int sum = 0;
        for (int value : A) {
            sum += value;
        }

        if (sum % 3 != 0) {
            return false;
        }

        int spert = sum / 3;
        int startP = -1,endP = A.length;

        int startSum = A[++startP];
        int endSum = A[--endP];

        while (startP < endP) {
            startSum = startSum == spert ? startSum : startSum + A[++startP];
            endSum = endSum == spert ? endSum : endSum + A[--endP];

            if (startP + 1 == endP) {
                break;
            }

            if (startSum == endSum && 3*startSum == sum) {
                return true;
            }
        }

        return false;
    }

    public String gcdOfStrings(String str1, String str2) {
        String same;
        if (str1.length() > str2.length()) {
            same = str2;
        } else {
            same = str1;
        }

        int str1Count = 0;
        int str2Count = 0;
        for (int i = 0; i < Integer.max(str1.length(), str2.length()); i++) {
            if (i < str1.length()) {
                str1Count += str1.charAt(i);
            }
            if (i < str2.length()) {
                str2Count += str2.charAt(i);
            }
        }
        int sameCount = Integer.min(str1Count, str2Count);

        while (same.length() > 1) {
            int str1Multiple = str1.length() / same.length();
            int str2Multiple = str2.length() / same.length();
            if (str1Count == sameCount *  str1Multiple
                    && str2Count == sameCount * str2Multiple) {
                return same;
            }

            sameCount -= same.charAt(same.length() - 1);
            same = same.substring(0, same.length() - 1);
        }

        return "";
    }

    public int majorityElement(int[] nums) {
        int maxNum = 0;
        int reNum = nums[0];

        Map<Integer, Integer> mapCount = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer value = mapCount.getOrDefault(nums[i], 0);
            value++;
            mapCount.put(nums[i], value);
            if (maxNum < value) {
                maxNum = value;
                reNum = nums[i];
            }
        }


        return reNum;
    }

    public String compressString(String S) {
        if (S.length() < 2) {
            return S;
        }
        StringBuilder sb = new StringBuilder();
        int sameCount = 0;
        char lastC = S.charAt(0);
        sb.append(lastC);
        for (char c : S.toCharArray()) {
            if (c != lastC) {
                sb.append(sameCount);
                sb.append(c);
                lastC = c;
                sameCount = 1;
                continue;
            }

            sameCount ++;
        }
        sb.append(sameCount);

        if (sb.length() >= S.length()) {
            return S;
        }

        return sb.toString();
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> reNodeList = new LinkedList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }

        while (!queue.isEmpty()) {
            int currentLadderCount = queue.size();
            List<Integer> currentLadderList = new ArrayList<>();

            for (int i = 0; i < currentLadderCount; i++) {
                root = queue.poll();
                if (root.left != null) queue.add(root.left);
                if (root.right != null) queue.add(root.right);

                currentLadderList.add(root.val);
            }

            reNodeList.addFirst(currentLadderList);
        }

        return reNodeList;
    }

    private int[] nums;

    public TreeNode sortedArrayToBST(int[] nums) {
        this.nums = nums;

        return loopToBST(0, nums.length - 1);
    }

    public TreeNode loopToBST(int leftCount, int rightCount) {
        if (leftCount > rightCount) return null;
        int middle = (leftCount + rightCount) / 2;

        TreeNode treeNode = new TreeNode(nums[middle]);
        treeNode.left = loopToBST(leftCount, middle - 1);
        treeNode.right = loopToBST(middle + 1, rightCount);

        return treeNode;
    }

    public int countCharacters(String[] words, String chars) {
        Map<Character, Integer> charCountMap = new HashMap<>();
        for (char workChar : chars.toCharArray()) {
            Integer count = charCountMap.getOrDefault(workChar, 0);
            charCountMap.put(workChar, ++count);
        }

        int length = 0;
        for (String word : words) {
            length += word.length();
            Map<Character, Integer> currentCharCountMap =  new HashMap<>(charCountMap);
            for (char currentChar : word.toCharArray()) {
                Integer charCount = currentCharCountMap.getOrDefault(currentChar, 0);
                if (charCount <= 0) {
                    length -= word.length();
                    break;
                }
                currentCharCountMap.put(currentChar, --charCount);
            }
        }

        return length;
    }

    public int minDepth(TreeNode root) {
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        if (root == null) {
            return 0;
        }

        queue.add(new Pair<>(root, 1));

        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> pair = queue.poll();
            TreeNode rootNode = pair.getKey();
            Integer depth = pair.getValue();

            if (rootNode.left == null && rootNode.right == null) {
                return depth;
            }

            if (rootNode.left != null) {
                queue.add(new Pair<>(rootNode.left, depth + 1));
            }

            if (rootNode.right != null) {
                queue.add(new Pair<>(rootNode.right, depth + 1));
            }
        }

        return -1;
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == sum;
        }
        return loopTreeNode(root, 0, sum);
    }

    public boolean loopTreeNode(TreeNode root, int currentSum, int targetSum) {
        if (root == null) return false;

        if (currentSum + root.val == targetSum
                && root.left == null
                && root.right == null) {
            return true;
        }
        else if (root.left == null && root.right == null) {
            return false;
        }

        boolean leftBoo = loopTreeNode(root.left, currentSum + root.val, targetSum);
        boolean rightBoo = loopTreeNode(root.right, currentSum + root.val, targetSum);


        return leftBoo || rightBoo;
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> reList = new ArrayList<>();
        if (numRows == 0) {
            return reList;
        }

        List<Integer> firstList = new ArrayList<>();
        firstList.add(1);
        reList.add(firstList);
        for (int i = 1; i < numRows; i++) {
            List<Integer> integerList = reList.get(i - 1);
            LinkedList<Integer> linkLastList = new LinkedList<>(integerList);
            linkLastList.addFirst(0);
            linkLastList.addLast(0);

            List<Integer> currentList = new ArrayList<>();
            for (int j = 0; j < linkLastList.size() - 1; j++) {

                currentList.add(linkLastList.get(j) + linkLastList.get(j + 1));
            }

            reList.add(currentList);
        }

        return reList;
    }

    /**
     * 836. 矩形重叠
     *
     * @param rec1
     * @param rec2
     * @return
     */
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        boolean rowB = Math.max(rec1[0], rec2[0]) < Math.min(rec1[2], rec2[2]);
        boolean colB = Math.max(rec1[1], rec2[1]) < Math.min(rec1[3], rec2[3]);

        return rowB && colB;
    }

    /**
     * 119. 杨辉三角 II
     *
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> triangleList = new ArrayList<>();
        triangleList.add(1);

        for (int j = 0; j < rowIndex; j++) {
            int zero = 0;
            for (int i = 0; i < triangleList.size(); i++) {
                int cur = triangleList.get(i);
                int sum = zero + cur;
                zero = cur;
                triangleList.set(i, sum);
            }
            triangleList.add(1);
        }

        return triangleList;
    }

    /**
     * 121.买卖股票的最佳时机
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> earnStack = new Stack<>();
        stack.push(prices[0]);
        earnStack.push(prices[0]);

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < stack.peek()) {
                stack.push(prices[i]);
            }
            if (earnStack.size() < stack.size()) {
                earnStack.push(prices[i]);
            } else if (earnStack.peek() < prices[i]) {
                earnStack.pop();
                earnStack.push(prices[i]);
            }
        }

        int max = 0;
        while (!stack.empty()) {
            /*if (earnStack.size() < stack.size()) {
                stack.pop();
                max = 0;
                continue;
            }*/

            max = Integer.max(max, earnStack.pop() - stack.pop());
        }

        return max;
    }

    public int maxProfit2(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> earnStack = new Stack<>();
        stack.push(prices[0]);
        earnStack.push(prices[0]);

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < earnStack.peek()) {
                stack.push(prices[i]);
            }
            if (earnStack.size() < stack.size()) {
                earnStack.push(prices[i]);
            } else if (earnStack.peek() < prices[i]) {
                earnStack.pop();
                earnStack.push(prices[i]);
            }
        }

        int max = 0;
        while (!stack.empty()) {
            max +=earnStack.pop() - stack.pop();
        }

        return max;
    }

    /**
     * 409. 最长回文串
     *
     * @param s
     * @return
     */
    public int longestPalindrome(String s) {
        int[] asciiArray = new int[58];

        for (char c : s.toCharArray()) {
            asciiArray[c - 'A'] += 1;
        }

        int ans = 0;
        for (int i : asciiArray) {
            ans += i - (i & 1);
        }

        return ans < s.length() ? ans + 1 : ans;
    }

    /**
     * 面试题29. 顺时针打印矩阵
     *
     * @param matrix
     * @return
     */
    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0) {
            return new int[0];
        }
        // 当前x位置 y的位置
        int xPosition = 0;
        int yPosition = 0;

        // true: x:step false: y:step
        boolean xyStep = true;

        // 当前x和y的步数
        int yStep = -1;
        int xStep = 1;

        int maxXSize = matrix.length;
        int maxYSize = matrix[0].length;

        // 转换点
        int changeMaxSize = maxYSize;
        // 每次x、y转换时加的树
        int changeYSize = maxYSize;
        int changXSize = maxXSize;

        int[] reArray = new int[maxXSize * maxYSize];
        int rePosition = 0;

        while (rePosition != reArray.length) {
            reArray[rePosition] = matrix[yPosition][xPosition];
            if (xyStep) {
                if (rePosition + 1 == changeMaxSize) {
                    xyStep = false;
                    changXSize--;
                    changeMaxSize = changeMaxSize + changXSize;
                    yStep = yStep < 0 ? 1 : -1;
                    yPosition = yPosition + yStep;
                } else {
                    xPosition = xPosition + xStep;
                }
            } else {
                if (rePosition + 1 == changeMaxSize) {
                    xyStep = true;
                    changeYSize--;
                    changeMaxSize = changeMaxSize + changeYSize;
                    xStep = xStep < 0 ? 1 : -1;
                    xPosition = xPosition + xStep;
                } else {
                    yPosition = yPosition + yStep;
                }
            }

            rePosition++;
        }

        return reArray;
    }

    /**
     * 面试题10- II. 青蛙跳台阶问题
     *
     * @param n
     * @return
     */
    public int numWays(int n) {
        if (n == 0) {
            return 1;
        }
        if (n <= 2) {
            return n;
        }
        int a=1,b=2,sum;

        for (int i = 2; i <= n; i++) {
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }

        return a;
        //return this.sumWays(0, n);
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        Stack<ListNode> nodeStack = new Stack<>();

        while (head != null) {
            nodeStack.push(head);
            head = head.next;
        }

        ListNode listNode = null;
        for (int i = 0; i < k; i++) {
            listNode = nodeStack.pop();
        }

        return listNode;
    }

    public int maxSubArray(int[] nums) {
        /*if (nums.length <= 1) {
            return nums[0];
        }
        int leftPosition= 0;
        int rightPosition = 1;

        int maxSum = nums[leftPosition];
        int sum = nums[leftPosition];
        while (leftPosition < nums.length - 1) {
            sum += nums[rightPosition];
            maxSum = Math.max(maxSum, sum);

            rightPosition ++;

            if (rightPosition > nums.length - 1) {
                leftPosition++;
                rightPosition = leftPosition;
                sum = 0;
            }
        }

        return Math.max(maxSum, nums[nums.length - 1]);*/

        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int num : nums) {
            if (sum < 0) {
                sum = num;
            } else {
                sum += num;
            }

            max = Integer.max(max, sum);
        }

        return max;
    }

    private int sumWays(int step, int n) {
        if (step > n) return 0;
        if (step == n) return 1;

        return sumWays( step + 1, n) + sumWays( step + 2, n);
    }

    /**
     * 面试题10- I. 斐波那契数列
     *
     * @param n
     * @return
     */
    public int fib(int n) {
        if (n == 0) {
            return 0;
        }
        int a = 0,b = 1,sum;
        for (int i = 1; i <= n; i++) {
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }

        return a;
    }

    public int[] exchange(int[] nums) {
        int leftP = 0;
        int rightP = nums.length - 1;

        int count = 0;

        int[] reI = new int[nums.length];
        while (count < nums.length) {
            if ((nums[count] & 1) == 1) {
                reI[leftP++] = nums[count];
            } else {
                reI[rightP--] = nums[count];
            }

            count++;
        }

        return reI;
    }

    public int hammingWeight(int n) {
        boolean negativeNum = n < 0;
        n = negativeNum ? n + 1 : n;
        n = Math.abs(n);
        int oneCount = 0;
        while (n > 1) {
            oneCount += n % 2;
            n = n >> 1;
        }
        oneCount += n;

        if (negativeNum) {
            oneCount = 32 - oneCount;
        }

        return oneCount;
    }

    public int add(int a, int b) {
        // 无进位和， 进位值
        int sum, carry;
        while (b != 0) {
            // 异或操作得无进位和
            sum = a ^ b;

            carry = a & b << 1;

            a = sum;
            b = carry;
        }

        return 0;

        /*String aBinary = Integer.toBinaryString(a);
        String bBinary = Integer.toBinaryString(b);

        int step = 0;

        char[] aChars = aBinary.toCharArray();
        int aCharLength = aChars.length - 1;
        char[] bChars = bBinary.toCharArray();
        int bCharLength = bChars.length - 1;

        char[] reChars = Math.abs(a) > Math.abs(b) ? aChars : bChars;
        int reLength = reChars.length - 1;

        for (int i = 0; i < Math.min(aChars.length, bChars.length); i++) {
            int sum = 0;
            if (aChars[aCharLength - i] == bChars[bCharLength - i]) {
                sum += step;
                if (aChars[aCharLength - i] == '1') {
                    step = 1;
                } else {
                    step = 0;
                }
            } else {
                sum = step == 1 ? 0 : 1;
                step = sum == 0 ? 1 : 0;
            }


            reChars[reLength - i] = Character.forDigit(sum, 2);
        }

        return Integer.parseInt(new String(reChars), 2);*/
    }

    /**
     * 367. 有效的完全平方数
     *
     * @param num
     * @return
     */
    public boolean isPerfectSquare(int num) {
        if (num == 0 || num == 1) {
            return true;
        }
        for (int i = 0; i < num; i++) {
            if (i*i == num) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.searchInsert(new int[]{1, 3, 5, 6}, 0));

        System.out.println(solution.countAndSay(5));

        System.out.println(Solution.lengthOfLastWord(""));

        System.out.println(Solution.plusOne(new int[]{9, 9}));

        System.out.println(Solution.addBirary("1111", "0010"));

        System.out.println(Solution.climbStairs(3));

        int[] nums1 = new int[]{4,5,6,0,0,0};
        int[] nums2 = new int[]{1,2,3};
        solution.merge(nums1,3,nums2,3);

        TreeNode left = new TreeNode(1);
        left.left = new TreeNode(2);
        left.right = new TreeNode(3);

        TreeNode right = new TreeNode(1);
        right.left = new TreeNode(2);
        right.right = new TreeNode(3);

        solution.isSameTree(left, right);

        TreeNode left1 = new TreeNode(2);
        left1.left = new TreeNode(3);
        left1.left.left = new TreeNode(4);
        left1.left.right = new TreeNode(5);

        left1.right = new TreeNode(3);
        left1.right.left = null;
        left1.right.right = new TreeNode(4);

        solution.isSymmetric(left1);

        TreeNode maxDepth = new TreeNode(3);
        maxDepth.left = new TreeNode(9);
        maxDepth.right = new TreeNode(20);
        maxDepth.right.left = new TreeNode(15);
        maxDepth.right.right = new TreeNode(7);
        solution.maxDepth(maxDepth);

        System.out.println(solution.canThreePartsEqualSum(new int[]{3,3,6,5,-2,2,5,1,-9,4}));

        System.out.println(solution.gcdOfStrings("ABABAB", "ABAB"));

        System.out.println(solution.majorityElement(new int[]{2, 2, 1, 1, 1, 2, 2}));

        System.out.println(solution.compressString("bb"));

        System.out.println(solution.levelOrderBottom(maxDepth));

        solution.sortedArrayToBST(new int[]{-10,-3,0,5,9});

        System.out.println(solution.countCharacters(
                new String[]{"cat", "bt", "hat", "tree"},
                "atach"));

        // [1,2,3,4,null,null,5]
        TreeNode minTree = new TreeNode(1);
        minTree.left = new TreeNode(2);
        minTree.right = new TreeNode(3);
        minTree.left.left = new TreeNode(4);
        minTree.right.right = new TreeNode(5);
        System.out.println(solution.minDepth(maxDepth));

        // same path
        TreeNode pathNode = new TreeNode(5);
        pathNode.left = new TreeNode(4);
        pathNode.right = new TreeNode(8);
        pathNode.left.left = new TreeNode(11);
        pathNode.left.left.left = new TreeNode(7);
        pathNode.left.left.right = new TreeNode(2);

        pathNode.right.left = new TreeNode(13);
        pathNode.right.right = new TreeNode(4);
        pathNode.right.right.right = new TreeNode(1);

        // same path
        TreeNode otherPathNode = new TreeNode(1);
        otherPathNode.left = new TreeNode(-2);
        otherPathNode.right = new TreeNode(3);

        System.out.println(solution.hasPathSum(pathNode, 1));

        System.out.println(solution.generate(5));

        System.out.println(solution.isRectangleOverlap(
                new int[]{5,15,8,18}, new int[]{0,3,7,9}));

        System.out.println(solution.getRow(3));

        System.out.println(solution.maxProfit2(new int[]{1,2,3,4,5}));

        System.out.println(solution.longestPalindrome("abccccdd"));

        // [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
        int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        System.out.println(solution.spiralOrder(matrix));

        System.out.println(solution.numWays(5));

        System.out.println(solution.fib(5));

        System.out.println(solution.maxSubArray(
                new int[]{8,-19,5,-4,20}));

        System.out.println(solution.hammingWeight(-3));

        System.out.println(solution.add(1, -2));

        System.out.println(solution.isPerfectSquare(9));
    }



    public static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }

    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }
}
