package com.wangwei.basic.alg;

public class OrderLinkedList {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode node = new ListNode(0);
        ListNode cur = node;
        while (l1 != null && l2 != null) {
            if (l1.val >= l2.val) {
                cur.next = new ListNode(l2.val);
                l2 = l2.next;
            } else {
                cur.next = new ListNode(l1.val);
                l1 = l1.next;
            }

            cur = cur.next;
        }

        cur.next = l1 == null ? l2 : l1;

        return node.next;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode l1 = new ListNode(1);
    public ListNode l2 = new ListNode(1);

    public static void main(String[] args) {
        OrderLinkedList orderLinkedList = new OrderLinkedList();
        orderLinkedList.l1.next = new ListNode(2);
        orderLinkedList.l1.next.next = new ListNode(4);

        orderLinkedList.l2.next = new ListNode(3);
        orderLinkedList.l2.next.next = new ListNode(4);


        orderLinkedList.mergeTwoLists(orderLinkedList.l1, orderLinkedList.l2);
    }

}
