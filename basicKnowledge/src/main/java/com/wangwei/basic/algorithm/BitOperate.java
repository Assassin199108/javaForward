package com.wangwei.basic.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BitOperate {
    
    public static void main(String[] args){
        String s = new String("dvdf");
        List<Character> link = new LinkedList<>();
        List<Character> compareLink = new LinkedList<>();
        int loop = 0;
        boolean firstContains = false;
        while (loop < s.length()) {
            loop++;

            char cur = s.charAt(loop - 1);
            firstContains = firstContains || link.contains(cur);

            if (firstContains && compareLink.contains(cur)) {
                if (compareLink.size() > link.size()) {
                    link = new LinkedList<>(compareLink);
                }
                compareLink.clear();
                compareLink.add(cur);
                continue;
            }

            if (!firstContains) {
                link.add(cur);
            }
            if (loop == 1) {
                continue;
            }
            compareLink.add(cur);
        }

        BitOperate bitOperate = new BitOperate();
        int[] ints = bitOperate.twoSum(new int[]{2, 11, 15, 7}, 9);
        System.out.println(ints);

        ListNode l1 = new ListNode(8);
        l1.next = new ListNode(9);
        l1.next.next = new ListNode(9);
        StringBuilder sb = new StringBuilder();
        bitOperate.iters(l1, sb);

        ListNode l2 = new ListNode(2);
        StringBuilder sb2 = new StringBuilder();
        bitOperate.iters(l2, sb2);

        bitOperate.transferString(sb, sb2);

        String[] chars = sb.toString().split(",");
        String[] chars2 = sb2.toString().split(",");

        List<String> list = new ArrayList<>();

        boolean hasLagerTen = false;
        for (int i = 0; i < chars.length; i++) {
            int sum = Integer.valueOf(chars[i]) + Integer.valueOf(chars2[i]);
            if (hasLagerTen) {
                sum = sum + 1;
            }
            if (sum >= 10) {
                list.add(String.valueOf(Integer.toString(sum).charAt(1)));
                hasLagerTen = true;
            } else {
                list.add(String.valueOf(sum));
                hasLagerTen = false;
            }
        }

        if (hasLagerTen) {
            list.add("1");
        }

        ListNode nodes = new ListNode(Integer.valueOf(list.get(0)));
        ListNode listNode = bitOperate.initilizeNode(nodes, list, 1, nodes);
        System.out.println(listNode);

        int a = 100;
        int b = 20;
        int cc = a ^ b;
        System.out.println(cc);
        int dd = (a & b)<<1;
        System.out.println(dd);
        while(dd != 0){
            cc = cc ^ dd;
            System.out.println(cc);
            dd = (cc & dd)<< 1;
            System.out.println(dd);
        }

        System.out.println("result:" + cc);

        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(-3));
        System.out.println(Integer.toBinaryString(-4));
        System.out.println(Integer.toBinaryString(-7));


        System.out.println(((a&b)<<1) | (a ^ b));

        System.out.println(-1 << 1);


        int c = 1;
        int d = 2;
        System.out.println(c >>> d);

        int f = 100;
        int g = 100;
        System.out.println(f | g);
    }

    public int[] twoSum(int[] nums, int target) {
        for(int i = 0; i < nums.length; i ++) {
            for (int j = i + 1; j < nums.length; j ++) {
                if (target == nums[i] + nums[j]) {
                    return new int[]{nums[i], nums[j]};
                }
            }
        }

        return null;
    }

    public void iters(ListNode nodes,StringBuilder sb) {
        sb.append(nodes.val);
        if (nodes.next == null) {
            return;
        }
        sb.append(",");
        this.iters(nodes.next, sb);

        return;
    }

    public ListNode initilizeNode(ListNode nodes, List<String> l , int loop, ListNode next) {
        if (loop >= l.size()) {
            return nodes;
        }
        ListNode ln = next.next = new ListNode(Integer.valueOf(l.get(loop)));
        loop++;

        return initilizeNode(nodes, l, loop, ln);
    }

    public void transferString(StringBuilder s1, StringBuilder s2) {
        if (s1.length() > s2.length()) {
            for (int i = 0; i < s1.length() - s2.length();) {
                s2.append(",");
                s2.append("0");
            }
        } else {
            for (int i = 0; i < s2.length() - s1.length(); i = i + 2) {
                s1.append(",");
                s1.append("0");
            }
        }
    }

    static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }
}
