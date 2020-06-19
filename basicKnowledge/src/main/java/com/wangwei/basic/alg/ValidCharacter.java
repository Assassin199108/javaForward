package com.wangwei.basic.alg;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidCharacter {

    private static Map<String, Character> map = new HashMap<>();

    // Hash table that takes care of the mappings.
    private static HashMap<Character, Character> mappings = new HashMap<>();

    static{
        map.put("\\(\\)", ')');
        map.put("\\{\\}", '}');
        map.put("\\[\\]", ']');

        mappings.put(')', '(');
        mappings.put('}', '{');
        mappings.put(']', '[');
    }

    /**
     * 双层for循环
     *
     * @param s
     * @return
     */
    public static boolean isValidSlow(String s) {
        if (s.startsWith(")")
                || s.startsWith("}")
                || s.startsWith("]")) {
            return false;
        }

        if (s.length() % 2 > 0) {
            return false;
        }

        String originS = "";
        while (!s.equals(originS)) {
            originS = s;
            for (Map.Entry<String, Character> entry : map.entrySet()) {
                s = s.replaceFirst(
                        entry.getKey(), "");

                if (s.length() <= 0) {
                    return true;
                }
            }
        }

        return s.length() <= 0;
    }

    public static boolean isValidStack(String s) {
        if (s.startsWith(")")
                || s.startsWith("}")
                || s.startsWith("]")) {
            return false;
        }

        if (s.length() % 2 > 0) {
            return false;
        }
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (mappings.containsKey(c)) {
                char pop = stack.isEmpty() ? '!' : stack.pop();

                if (pop != mappings.get(c)) {
                    return false;
                }
            } else {

                stack.push(c);
            }

        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isValidSlow("{[]}"));

        System.out.println(isValidStack("{[]}"));
    }

}
