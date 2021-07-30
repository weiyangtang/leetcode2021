

package com.leetcode.stack;

import java.util.Stack;

/**
 * @author bjhl
 * @description 最长括号
 * @date 2021/5/29 下午2:35
 **/
public class LongestValidParentheses {

    public static int longestValidParentheses(String s) {
        StringBuilder sb = new StringBuilder();
        int max = 0;
        int len = s.length();
        int[] tag = new int[len];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '(' && s.charAt(i) == ')') {
                sb.deleteCharAt(sb.length() - 1);
                Integer pop = stack.pop();
                tag[pop] = tag[i] = 1;
            } else {
                sb.append(s.charAt(i));
                stack.push(i);
            }
        }
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (tag[i] == 1) {
                count++;
            } else {
                max = Math.max(max, count);
                count = 0;
            }
        }
        max = Math.max(max, count);
        return max;
    }

    public static void main(String[] args) {
        int max = longestValidParentheses("(()((()())");
        System.out.println(max);
    }
}
