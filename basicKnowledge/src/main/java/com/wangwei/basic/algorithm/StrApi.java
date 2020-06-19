package com.wangwei.basic.algorithm;

import java.util.*;

public class StrApi {

    /**
     * 面试题58 - I. 翻转单词顺序
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        if (s.length() == 0) {
            return "";
        }
        String[] strings = s.split(" +");

        StringBuilder sb = new StringBuilder();
        for (int i = strings.length - 1; i >= 0 ; i--) {
            String string = strings[i];

            sb.append(string).append(" ");
        }

        return sb.toString().trim();
    }

    /**
     * 面试题50. 第一个只出现一次的字符
     *
     * @param s
     * @return
     */
    public char firstUniqChar(String s) {
        if (s.length() == 0) {
            return ' ';
        }
        for (char c : s.toCharArray()) {
            if (s.indexOf(c) == s.lastIndexOf(c)) {
                return c;
            }
        }

        return ' ';
    }

    public String replaceSpace(String s) {
        char[] reChar = new char[s.length() * 3];
        char[] replaceChar = new char[]{'%', '2', '0'};
        int position = 0;
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                reChar[position++] = replaceChar[0];
                reChar[position++] = replaceChar[1];
                reChar[position++] = replaceChar[2];
            } else {
                reChar[position++] = c;
            }
        }

        return new String(Arrays.copyOf(reChar, position));
    }

    public String reverseLeftWords(String s, int n) {
        String substring = s.substring(0, n);
        return s.substring(n) + substring;
    }

    public boolean wordPattern(String pattern, String str) {
        String[] strs = str.split(" ");

        if (strs.length != pattern.length()) return false;

        Map<Object, Integer> map = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {

            Integer patternI = map.put(pattern.charAt(i), i);
            Integer strI = map.put(strs[i], i);
            if (!Objects.equals(patternI, strI)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 8. 字符串转换整数 (atoi)
     *
     * @param str
     * @return
     */
    public int myAtoi(String str) {
        if (str == null || str.trim().length() == 0) {
            return 0;
        }
        str = str.trim();

        StringBuilder sb = new StringBuilder();
        sb.append(0);
        boolean negative = false;
        boolean plus = false;

        if (str.startsWith("-")) {
            negative = true;
        } else if (str.startsWith("+")) {
            plus = true;
        } else if (Character.digit(str.charAt(0), 10) > 9) {
            return 0;
        }
        for (int i = negative || plus ? 1 : 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);
            if (!Character.isDigit(currentChar)) {
                break;
            }
            int digit = Character.digit(currentChar, 10);
            long originDigit = Long.parseLong(sb.toString());
            long maxValue = originDigit * 10 + digit;

            if (negative && maxValue > Integer.MAX_VALUE) {
                return Integer.MIN_VALUE;
            }

            if (!negative && maxValue > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }

            sb.append(digit);
        }

        if (sb.length() == 0) {
            return 0;
        }

        if (negative && sb.length() == 1) {
            return 0;
        }

        return negative
                ? -Integer.parseInt(sb.toString())
                : Integer.parseInt(sb.toString());
    }

    /**
     * 551.学生出勤记录
     *
     * @param s
     * @return
     */
    public boolean checkRecord(String s) {
        boolean hasA = false;
        int lNum = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'L') {
                if (lNum++ >= 2) {
                    return false;
                }
            } else {
                lNum = 0;
            }

            if (s.charAt(i) == 'A') {
                if (hasA) {
                    return false;
                }
                hasA = true;
            }
        }

        return true;
    }

    /**
     * 面试题 01.03. URL化
     *
     * @param S
     * @param length
     * @return
     */
    public String replaceSpaces(String S, int length) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < length) {
            char c = S.charAt(i++);
            sb.append(c == ' ' ? "%20" : c);
        }

        return sb.toString();
    }

    public boolean isFlipedString(String s1, String s2) {
        if (s1.length() == 0 && s2.length() == 0) {
            return true;
        }
        if (s1.length() == 0 || s2.length() == 0) {
            return false;
        }
        String mergeS = s1 + s1;

        return mergeS.contains(s2);
    }

    public static void main(String[] args) {

        StrApi strApi = new StrApi();
        System.out.println(strApi.reverseWords("a good   example"));

        System.out.println(strApi.firstUniqChar("abaccdeff"));

        System.out.println(strApi.wordPattern("abba",
                "dog cat cat dog"));

        System.out.println(strApi.myAtoi("-6147483648"));

        System.out.println(strApi.checkRecord("PPALLL"));

        System.out.println(strApi.replaceSpaces("               ", 5));

        System.out.println(strApi.isFlipedString("a", "a"));
    }

}
