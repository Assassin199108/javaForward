package com.wangwei.basic.algorithm;

import java.util.*;

public class ListCalculate {

    /**
     * 876. 链表的中间结点
     * 快慢指针法
     *
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        // 快指针
        ListNode fastNode = head;

        // 慢指针
        while (fastNode != null && fastNode.next != null) {
            fastNode = fastNode.next.next;
            head = head.next;
        }

        return head;
    }

    public int[] constructArr(int[] a) {
        int sum = 1;
        int[] reI = new int[a.length];
        int zero = -1;
        int zeroCount = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == 0) {
                zero = i;
                zeroCount++;
                continue;
            }
            sum *= a[i];
        }

        if (zeroCount > 1) {
            return reI;
        }
        if (zero > 0) {
            reI[zero] = sum;
            return reI;
        }


        for (int i = 0; i < a.length; i++) {
            reI[i] = sum / a[i];
        }

        return reI;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        ListNode reNode = new ListNode(-1);
        ListNode stepNode = reNode;
        while (l1 != null && l2 != null) {
            if (l1.val >= l2.val) {
                stepNode = stepNode.next = new ListNode(l2.val);
                l2 = l2.next;
            } else {
                stepNode = stepNode.next = new ListNode(l1.val);
                l1 = l1.next;
            }
        }

        stepNode.next = l1 != null ? l1 : l2;

        return reNode.next;
    }

    /**
     * 面试题24. 反转链表
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        //申请节点，pre和 cur，pre指向null
        ListNode pre = null;
        ListNode cur = head;
        ListNode tmp = null;
        while(cur!=null) {
            //记录当前节点的下一个节点
            tmp = cur.next;
            //然后将当前节点指向pre
            cur.next = pre;
            //pre和cur节点都前进一位
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    int[] arrays = new int[10];
    int size = 0;
    int capacity = 10;
    int min = Integer.MAX_VALUE;

    public void push(int x) {
        if (this.size > arrays.length) {
            arrays = Arrays.copyOf(arrays, arrays.length + capacity);
        }

        arrays[size++] = x;
        min = Math.min(x, min);
    }

    public void pop() {
        min = min == top() ? Integer.MAX_VALUE : min;
        size--;
        for (int i = 0; i < size; i++) {
            min = Math.min(arrays[i], min);
        }
    }

    public int top() {
        return arrays[size - 1];
    }

    public int min() {
        return min;
    }

    public ListNode deleteNode(ListNode head, int val) {
        ListNode reNode = new ListNode(-1);
        ListNode stepNode = reNode;
        while (head.val != val) {
            stepNode = stepNode.next = new ListNode(head.val);
            head = head.next;
        }

        stepNode.next = head.next;
        return reNode.next;
    }

    public int[] reversePrint(ListNode head) {
        if (head == null) {
            return new int[0];
        }
        ListNode slowP = head;
        ListNode fastNode = head;
        int length = 0;

        while (fastNode != null && fastNode.next != null) {
            length = length + 2;
            slowP = slowP.next;
            fastNode = fastNode.next.next;
        }
        if (fastNode != null) {
            length++;
        }

        int[] reI = new int[length];
        int mid = length / 2;
        int left = mid - 1;
        int right = length - 1;
        if (length % 2 != 0) {
            reI[mid] = slowP.val;
            slowP = slowP.next;
        }


        while (left >= 0) {
            reI[left--] = slowP.val;
            reI[right--] = head.val;
            slowP = slowP.next;
            head = head.next;
        }

        return reI;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        /*

        while (aCopy != null && bCopy != null) {
            aCopy = aCopy.next;
            bCopy = bCopy.next;
        }*/

        ListNode reNode = null;

        while (headA != null) {
            int aVal = headA.val;
            reNode = headA;
            ListNode bCopy = headB;
            while (bCopy != null && bCopy.val != aVal) {
                bCopy = bCopy.next;
            }

            while (bCopy != null && headA == bCopy) {
                headA = headA.next;
                bCopy = bCopy.next;
            }

            if (headA != null || bCopy != null) {
                headA = reNode.next;
                reNode = reNode.next;
            }
        }

        return reNode;
    }

    public void deleteNode(ListNode node) {
        if (node == null) {
            return;
        }

        ListNode fastNode = node.next.next;
        ListNode slowNode = node;

        while (fastNode != null &&
                fastNode.next != null) {

            if (fastNode.next.next != null) {
                slowNode = slowNode.next;
            }
            fastNode = fastNode.next.next;
        }

        slowNode.next = slowNode.next.next;
    }

    public static void main(String[] args) {
        ListCalculate listCalculate = new ListCalculate();

        listCalculate.constructArr(new int[]{1, 2, 0, 4, 5});

        ListNode listNodeOne = new ListNode(-9);
        listNodeOne.next = new ListNode(3);

        ListNode listNodeTwo = new ListNode(5);
        listNodeTwo.next = new ListNode(7);
        listCalculate.mergeTwoLists(listNodeOne, listNodeTwo);

        ListNode reverseNode = new ListNode(1);
        reverseNode.next = new ListNode(2);
        reverseNode.next.next = new ListNode(3);
        reverseNode.next.next.next = new ListNode(4);
        reverseNode.next.next.next.next = new ListNode(5);
        listCalculate.reverseList(reverseNode);

        listCalculate.push(-2);
        listCalculate.push(0);
        listCalculate.push(-3);
        int min = listCalculate.min();
        int[] arrays = listCalculate.arrays;
        listCalculate.pop();
        arrays = listCalculate.arrays;
        int top = listCalculate.top();
        min = listCalculate.min();
        System.out.println(min);

        ListNode reversePrintNode = new ListNode(8);
        reversePrintNode.next = new ListNode(4);
        reversePrintNode.next.next = new ListNode(0);
        reversePrintNode.next.next.next = new ListNode(6);
        reversePrintNode.next.next.next.next = new ListNode(5);
        reversePrintNode.next.next.next.next.next = new ListNode(6);
        reversePrintNode.next.next.next.next.next.next = new ListNode(5);
        reversePrintNode.next.next.next.next.next.next.next = new ListNode(7);

        /*ListNode reversePrintNode = new ListNode(1);
        reversePrintNode.next = new ListNode(3);
        reversePrintNode.next.next = new ListNode(2);*/

        listCalculate.reversePrint(reversePrintNode);

        ListNode headA = new ListNode(2);
        headA.next = new ListNode(6);
        headA.next.next = new ListNode(4);
        // headA.next.next.next = new ListNode(4);
        // headA.next.next.next.next = new ListNode(5);

        ListNode headB = new ListNode(1);
        headB.next = new ListNode(5);
        // headB.next.next = new ListNode(1);
        // headB.next.next.next = new ListNode(8);
        // headB.next.next.next.next = new ListNode(4);
        // headB.next.next.next.next.next = new ListNode(5);

        System.out.println(listCalculate.getIntersectionNode(headA, headB));

        ListNode deleteNode = new ListNode(4);
        deleteNode.next = new ListNode(5);
        deleteNode.next.next = new ListNode(1);
        deleteNode.next.next.next = new ListNode(9);
        listCalculate.deleteNode(deleteNode);
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

}
