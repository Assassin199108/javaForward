package com.wangwei.basic.algorithm;

import java.util.*;

/**
 * 二叉搜索树（Binary Search Tree），（又：二叉查找树，二叉排序树）
 * 它或者是一棵空树，或者是具有下列性质的二叉树：
 * 若它的左子树不空，则左子树上所有结点的值均小于它的根结点的值；
 * 若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值；
 * 它的左、右子树也分别为二叉搜索树。
 *
 * 时刻掌握：前序遍历,后序遍历,中序遍历, BSF DSF
 *
 */
public class TreeCalculate {

    /**
     * 面试题55 - II. 平衡二叉树
     * 时间复杂度: o(Nlog N)
     * 空间复杂度: o(N)
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        return Math.abs(
                treeDepth(root.left)
                        - treeDepth(root.right)) <= 1
                && isBalanced(root.left)
                && isBalanced(root.right);
    }

    public int treeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(
                treeDepth(root.left) + 1,
                treeDepth(root.right) + 1);
    }

    /**
     * 面试题28. 对称的二叉树
     * 时间复杂度:o(N)
     * 空间复杂度:o(N)
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        return this.isSameTree(root.left, root.right);
    }

    public boolean isSameTree(TreeNode leftTree, TreeNode rightTree) {
        if (leftTree == null && rightTree == null) {
            return true;
        } else if (leftTree == null || rightTree == null) {
            return false;
        }

        if (leftTree.val != rightTree.val) {
            return false;
        }

        return isSameTree(leftTree.left, rightTree.right)
                && isSameTree(leftTree.right, rightTree.left);
    }

    /**
     * 面试题32 - II. 从上到下打印二叉树 II
     * BFS 循环
     *
     * 时间复杂度:o(N)
     * 空间复杂度:o(N)
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> reList = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> embeddedList = new ArrayList<>();

            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                embeddedList.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }

            reList.add(embeddedList);
        }

        return reList;
    }

    /**
     * 面试题27. 二叉树的镜像
     * 核心代码：
     *  TreeNode temp = node.left;
     *  node.left = node.right;
     *  node.right = temp;
     *
     *  后续遍历 递归
     *
     *
     * @param root
     * @return
     */
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode temp = root.left;
        root.left = this.mirrorTree(root.right);
        root.right = this.mirrorTree(temp);

        return root;
    }

    /**
     * 前序遍历非递归
     *
     * @param root
     * @return
     */
    public TreeNode mirrorTreeBeforeOrder(TreeNode root) {
        if (root == null){
            return null;
        }
        TreeNode node = root;
        Deque<TreeNode> stack = new LinkedList<>();
        while (node != null || !stack.isEmpty()){
            if (node != null){
                // 交换左右子树
                inventTreeNode(node);
                // 原先的先序遍历
                stack.push(node);
                node = node.left;
            }else {
                node = stack.pop();
                node = node.right;
            }
        }
        return root;
    }

    /**
     * 中序遍历非递归
     *
     * @param root
     * @return
     */
    public TreeNode mirrorTreeMiddleOrder(TreeNode root) {
        if (root == null){
            return null;
        }
        TreeNode node = root;
        Deque<TreeNode> stack = new LinkedList<>();
        while (node != null || !stack.isEmpty()){
            if (node != null){
                stack.push(node);
                node = node.left;
            }else {
                node = stack.pop();
                // 交换左右子树
                inventTreeNode(node);
                // 这里因为交换了，不能继续往右子树走，因为现在的右子树，是原来的左子树
                // 所以接下来要走左边
                node = node.left;
            }
        }
        return root;
    }

    /**
     * 后序遍历非递归
     *
     * @param root
     * @return
     */
    public TreeNode mirrorTreeAfterOrder(TreeNode root) {
        if (root == null){
            return null;
        }
        TreeNode node = root;
        TreeNode tempNode = null;// 记录上次访问的节点
        Deque<TreeNode> stack = new LinkedList<>();
        while (node != null || !stack.isEmpty()){
            if (node != null){
                stack.push(node);
                node = node.left;
            } else {
                node = stack.peek();
                if (node.right == null || node.right == tempNode){
                    // 右节点为空，或者右节点已经被访问过
                    // 当前节点出栈
                    tempNode = stack.pop();
                    // 交换
                    inventTreeNode(node);
                    // 这里要置空，不然node还是存在，又会往左子树去了
                    node = null;
                }else {
                    node = node.right;
                }
            }
        }
        return root;
    }

    private void inventTreeNode(TreeNode node){
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
    }

    /**
     * 面试题55 - I. 二叉树的深度
     * DFS
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {


        return maxDepthWithOutRoot(root) + 1;
    }

    public int maxDepthWithOutRoot(TreeNode root) {
        if (root == null) {
            return -1;
        }

        return Math.max(
                maxDepthWithOutRoot(root.left) + 1,
                maxDepthWithOutRoot(root.right) + 1);
    }

    /**
     * 面试题55 - I. 二叉树的深度
     * BFS
     *
     * @param root
     * @return
     */
    public int maxDepthBSF(TreeNode root) {
        if(root == null) return 0;
        List<TreeNode> queue = new LinkedList() {{ add(root); }},
                tmp;
        int res = 0;
        while(!queue.isEmpty()) {
            tmp = new LinkedList<>();
            for(TreeNode node : queue) {
                if(node.left != null) tmp.add(node.left);
                if(node.right != null) tmp.add(node.right);
            }
            queue = tmp;
            res++;
        }
        return res;
    }


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root.val == q.val || root.val == p.val) return root;

        TreeNode leftTree = lowestCommonAncestor(root.left, p, q);
        TreeNode rightTree = lowestCommonAncestor(root.right, p, q);

        if (leftTree == null) return rightTree;
        if (rightTree == null) return leftTree;

        return root;
    }

    /**
     * 面试题68 - II. 二叉树的最近公共祖先
     * 最近公共祖先（最近公共节点）
     *
     * 根据题目给定的前提：
     * 所有节点的值都是唯一的。
     * p、q 为不同节点且均存在于给定的二叉搜索树中。
     *
     * 说明有以下几种情况：
     * 二叉树本身为空，root == null ，return root
     * p.val == q.val ,一个节点也可以是它自己的祖先
     * p.val 和 q.val 都小于 root.val
     * (两个子节点的值都小于根节点的值，说明它们的公共节点只能在二叉树的左子树寻找）
     * p.val 和 q.val 都大于 root.val
     * (两个子节点的值都大于根节点的值，说明它们的公共节点只能在二叉树的右子树寻找）
     * 如果上述的情况皆不满足，说明其公共节点既不在左子树也不在右子树上，只能为最顶端的公共节点，return root
     * p.val < root.val && q.val > root.val 或 p.val > root.val && q.val < root.val
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val<root.val && q.val<root.val){
            return lowestCommonAncestor(root.left,p,q);
        }else if (p.val>root.val && q.val>root.val){
            return lowestCommonAncestor(root.right,p,q);
        }

        return root;
    }

    int count = 0;
    int result = -1;

    /**
     * 面试题54. 二叉搜索树的第k大节点
     * 具体查看网页保存 有具体的图纸解析
     * 有点难
     *
     * @param root
     * @param k
     * @return
     */
    public int kthLargest(TreeNode root, int k) {
        if (root == null) {
            return 0;
        }
        if (k == 0) {
            return root.val;
        }

        count = k;
        kthLargest(root);
        return result;
    }

    public void kthLargest(TreeNode root) {
        if (root != null) {
            kthLargest(root.right);
            if (count == 1) {
                result = root.val;
                count --;
                return;
            }

            count--;
            kthLargest(root.left);
        }
    }

    /**
     * 面试题 17.12. BiNode
     * 非递归，BST中序遍历是有序的，我们只要对树进行中序遍历即可，
     * 利用一个前驱节点prev，记录上一个被处理的节点，当前节点被遍历到的时候，
     * 将prev.right指向当前节点node，然后node.left置空，
     * prev指针后移到node,最后node进入右子树即可
     *
     *
     * @param root
     * @return
     */
    public TreeNode traverseConvertBiNode(TreeNode root) {
        TreeNode treeNode = new TreeNode(0);
        TreeNode prev = treeNode;

        TreeNode node = root;

        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.add(node);
                node = node.left;
            } else {
                node = stack.pop();
                node.left = null;
                prev.right = node;
                prev = node;
                node = node.right;
            }
        }

        return treeNode.right;
    }

    /**
     * 面试题 17.12. BiNode
     * 归，思路基本与非递归相同，不过要注意的是递归之后的prev指针要返回，
     * 因为JAVA中是没有引用传递的，左子树递归回来之后，
     * 当前的prev指针没有发生改变，还是外部传进来的那个哨兵节点，这个时候一旦进入右子树，
     * 之前的prev.right指针将会被重置。也就是说，root的左子树操作全部失效了
     *
     *
     * @param root
     * @return
     */
    public TreeNode recursiveConvertBiNode(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode treeNode = new TreeNode(0);
        this.loopNextStep(root, treeNode);

        return treeNode.right;
    }

    public TreeNode loopNextStep(TreeNode root, TreeNode prev) {
        if (root != null) {
            prev = loopNextStep(root.left, prev);
            root.left = null;
            prev.right = root;
            prev = root;
            prev = loopNextStep(root.right, prev);
        }

        return prev;
    }

    /**
     * 1022. 从根到叶的二进制数之和
     * 二进制数转换成十进制整数就是乘2的问题,比如1010=2^(3)*1+2^(2)*0+2^(1)*1+2^(0)*0=10.
     * 1 当我们遍历的根节点时，根节点是二进制的最高位，直到遍历到叶子结点之前，
     * 每次遍历到子节点时都让根节点乘2，其他节点也是如此。
     *
     *
     * @param root
     * @return
     */
    public int sumRootToLeaf(TreeNode root) {
        if (root == null) {
            return 0;
        }

        loopSum(root, 0);
        return sum;
    }

    int sum = 0;

    public void loopSum(TreeNode root, int num) {
        if (root == null) return;
        num += root.val;
        if(root.left==null&&root.right==null){
            sum+=num;
        }

        loopSum(root.left, num*2);
        loopSum(root.right, num*2);
    }

    /**
     * 653. 两数之和 IV - 输入 BST
     *
     * @param root
     * @param k
     * @return
     */
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }

        this.loopFillList(root);
        int leftP = 0;
        int rightP = list.size() - 1;

        while (leftP < rightP) {
            int sum = list.get(leftP) + list.get(rightP);
            if (sum == k) {
                return true;
            } else if (sum < k) {
                leftP++;
            } else {
                rightP--;
            }
        }

        return false;
    }

    List<Integer> list = new ArrayList<>();

    public void loopFillList(TreeNode node) {
        if (node != null) {
            loopFillList(node.left);
            list.add(node.val);
            loopFillList(node.right);
        }
    }

    boolean isSubTree = false;

    /**
     * 572. 另一个树的子树
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        dsfSubtree(s, t);
        return isSubTree;
    }

    /**
     * 先中序遍历
     * 不过标准答案应该是先先序遍历比较快
     * 遍历weitrue后 没有及时return
     *
     * @param s
     * @param t
     */
    public void dsfSubtree(TreeNode s, TreeNode t) {
        if (s != null) {
            dsfSubtree(s.left, t);
            if (loopIsSub(s, t)) {
                isSubTree = true;
            }
            dsfSubtree(s.right, t);
        }
    }

    public boolean loopIsSub(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }

        if (s.val != t.val) {
            return false;
        }

        return loopIsSub(s.left, t.left) && loopIsSub(s.right, t.right);
    }

    /**
     * 404. 左叶子之和
     *
     * @param root
     * @return
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 0;
        }

        return middleLoopSum(root, false);
    }

    public int middleLoopSum(TreeNode node, Boolean leftNode) {
        if (node == null) {
            return 0;
        }

        if (node.left == null && node.right == null) {
            if (leftNode) {
                return node.val;
            } else {
                return 0;
            }
        }

        return middleLoopSum(node.left, true)
                + middleLoopSum(node.right, false);
    }

    /**
     * 669. 修剪二叉搜索树
     *
     * @param root
     * @param L
     * @param R
     * @return
     */
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) {
            return null;
        }
        if (root.val < L) {
            return trimBST(root.right, L, R);
        }
        if (root.val > R) {
            return trimBST(root.left, L, R);
        }
        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);
        return root;
    }

    int maxSize=0;
    Map<Integer, Integer> countMap = new HashMap<>();

    public int[] findMode(TreeNode root) {

        int[] votes = new int[0];
        int num = 0;
        middleIter(root);
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() >= maxSize) {
                votes = Arrays.copyOf(votes, votes.length + 1);
                votes[num++] = entry.getKey();
            }
        }

        return votes;
    }

    public void middleIter(TreeNode root) {
        if (root != null) {
            Integer defaultCount = countMap.getOrDefault(root.val, 0);
            countMap.put(root.val, ++defaultCount);
            maxSize = Math.max(maxSize, defaultCount);

            middleIter(root.left);
            middleIter(root.right);
        }
    }

    /**
     * 543. 二叉树的直径
     * 不会
     *
     * @param root
     * @return
     */
    int ans;
    public int diameterOfBinaryTree(TreeNode root) {
        ans = 1;
        depth(root);
        return ans - 1;
    }
    public int depth(TreeNode node) {
        if (node == null) return 0; // 访问到空节点了，返回0
        int L = depth(node.left); // 左儿子为根的子树的深度
        int R = depth(node.right); // 右儿子为根的子树的深度
        ans = Math.max(ans, L+R+1); // 计算d_node即L+R+1 并更新ans
        return Math.max(L, R) + 1; // 返回该节点为根的子树的深度
    }

    /**
     * 938. 二叉搜索树的范围和
     *
     * @param root
     * @param L
     * @param R
     * @return
     */
    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) return 0;
        if (root.val < L) return rangeSumBST(root.right, L, R);
        if (root.val > R) return rangeSumBST(root.left, L, R);

        return root.val + rangeSumBST(root.left, L, R) + rangeSumBST(root.right, L, R);
    }

    /**
     * 94. 二叉树的中序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();

        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.add(root);
                root = root.left;
            } else {
                TreeNode treeNode = stack.pop();
                list.add(treeNode.val);
                root = treeNode.right;
            }
        }

        return list;
    }

    public static void main(String[] args) {
        TreeCalculate treeCalculate = new TreeCalculate();

        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(2);
        treeNode.left.left = new TreeNode(3);

        treeNode.right.right = new TreeNode(3);
        treeNode.left.left.left = new TreeNode(4);

        treeNode.right.right.right = new TreeNode(4);

        System.out.println(treeCalculate.isBalanced(treeNode));

        TreeNode treeNode2 = new TreeNode(1);
        treeNode2.left = new TreeNode(2);
        treeNode2.right = new TreeNode(2);
        treeNode2.left.left = new TreeNode(3);
        treeNode2.left.right = new TreeNode(4);
        treeNode2.right.left = new TreeNode(4);
        treeNode2.right.right = new TreeNode(3);
        System.out.println(treeCalculate.isSymmetric(treeNode2));

        TreeNode mirrorNode = new TreeNode(4);
        mirrorNode.left = new TreeNode(2);
        mirrorNode.right = new TreeNode(7);
        mirrorNode.left.left = new TreeNode(1);
        mirrorNode.left.right = new TreeNode(3);
        mirrorNode.right.left = new TreeNode(6);
        mirrorNode.right.right = new TreeNode(9);
        System.out.println(treeCalculate.mirrorTree(mirrorNode));

        TreeNode depthNode = new TreeNode(3);
        depthNode.left = new TreeNode(9);
        depthNode.right = new TreeNode(20);

        depthNode.right.left = new TreeNode(15);
        depthNode.right.right = new TreeNode(7);
        System.out.println(treeCalculate.maxDepth(depthNode));

        TreeNode commonNode = new TreeNode(1);
        commonNode.left = new TreeNode(2);
        commonNode.right = new TreeNode(3);
        System.out.println(treeCalculate.lowestCommonAncestor(commonNode, new TreeNode(3), new TreeNode(2)));

        TreeNode largestNode = new TreeNode(5);
        largestNode.left = new TreeNode(3);
        largestNode.right = new TreeNode(6);
        largestNode.left.left = new TreeNode(2);
        largestNode.left.right = new TreeNode(4);
        largestNode.left.left.left = new TreeNode(1);

        System.out.println(treeCalculate.kthLargest(largestNode, 3));

        TreeNode biNode = new TreeNode(4);
        biNode.left = new TreeNode(2);
        biNode.right = new TreeNode(5);
        biNode.left.left = new TreeNode(1);
        biNode.left.right = new TreeNode(3);
        biNode.right.right = new TreeNode(6);
        biNode.left.left.left = new TreeNode(0);
        //System.out.println(treeCalculate.traverseConvertBiNode(biNode));

        System.out.println(treeCalculate.recursiveConvertBiNode(biNode));

        TreeNode sumRoot = new TreeNode(1);
        sumRoot.left = new TreeNode(0);
        sumRoot.right = new TreeNode(1);
        sumRoot.left.left = new TreeNode(0);
        sumRoot.left.right = new TreeNode(1);
        sumRoot.right.left = new TreeNode(0);
        sumRoot.right.right = new TreeNode(1);
        System.out.println(treeCalculate.sumRootToLeaf(sumRoot));

        TreeNode findTarget = new TreeNode(5);
        findTarget.left = new TreeNode(3);
        findTarget.right = new TreeNode(6);
        findTarget.left.left = new TreeNode(2);
        findTarget.left.right = new TreeNode(4);
        findTarget.right.right = new TreeNode(7);
        System.out.println(treeCalculate.findTarget(findTarget, 28));

        TreeNode fatherTree = new TreeNode(3);
        fatherTree.left = new TreeNode(4);
        fatherTree.right = new TreeNode(5);
        fatherTree.left.left = new TreeNode(1);
        fatherTree.left.right = new TreeNode(2);

        TreeNode subTree = new TreeNode(4);
        subTree.left = new TreeNode(1);
        subTree.right = new TreeNode(2);
        System.out.println(treeCalculate.isSubtree(fatherTree, subTree));

        TreeNode leftLeavesNode = new TreeNode(1);
        leftLeavesNode.left = new TreeNode(2);
        leftLeavesNode.left.left = new TreeNode(3);
        leftLeavesNode.left.left.left = new TreeNode(4);
        leftLeavesNode.left.left.left.left = new TreeNode(5);
        System.out.println(treeCalculate.sumOfLeftLeaves(leftLeavesNode));

        TreeNode trimBSTNode = new TreeNode(3);
        trimBSTNode.left = new TreeNode(1);
        trimBSTNode.right = new TreeNode(4);
        trimBSTNode.left.right = new TreeNode(2);
        System.out.println(treeCalculate.trimBST(trimBSTNode, 3, 4));

        TreeNode findModeNode = new TreeNode(1);
        findModeNode.right = new TreeNode(2);
        findModeNode.right.left = new TreeNode(2);
        treeCalculate.findMode(findModeNode);

        TreeNode diameterOfBinaryTree = new TreeNode(1);
        diameterOfBinaryTree.left = new TreeNode(2);
        diameterOfBinaryTree.left.left = new TreeNode(4);
        diameterOfBinaryTree.left.right = new TreeNode(5);
        diameterOfBinaryTree.right = new TreeNode(3);

        System.out.println(treeCalculate.diameterOfBinaryTree(diameterOfBinaryTree));

        // root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
        TreeNode rangeSumBST = new TreeNode(10);
        rangeSumBST.left = new TreeNode(5);
        rangeSumBST.right = new TreeNode(15);
        rangeSumBST.left.left = new TreeNode(3);
        rangeSumBST.left.right = new TreeNode(7);
        rangeSumBST.right.left = new TreeNode(13);
        rangeSumBST.right.right = new TreeNode(18);
        rangeSumBST.left.left.left = new TreeNode(1);
        rangeSumBST.left.right.left = new TreeNode(6);

        System.out.println(treeCalculate.rangeSumBST(rangeSumBST, 6, 10));

        // root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
        TreeNode inorderTraversal = new TreeNode(1);
        inorderTraversal.right = new TreeNode(2);
        inorderTraversal.right.left = new TreeNode(3);
        System.out.println(treeCalculate.inorderTraversal(inorderTraversal));

    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

}
