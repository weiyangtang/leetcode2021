package com.leetcode.dp;

/**
 * @author tangweiyang
 * @Description:
 * @date 2021/3/7 上午1:40
 */
public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        int size = s.length();
        int[][] plain = new int[size][size];
        int max = 0;
        int left = 0, right = 0;
        for (int i = 1; i <= size - 1; i++) {
            for (int j = 0; j + i <= size - 1; j++) {
                boolean flag = (i == 1 || i == 2 || plain[j + 1][j + i - 1] == 1) && s.charAt(j) == s.charAt(j + i);
                if (flag) {
                    plain[j][j + i] = 1;
                    if (max < i + 1) {
                        max = i + 1;
                        left = j;
                        right = j + i;
                    }
                }
            }
        }
        return s.substring(left, right + 1);
    }

    public static void main(String[] args) {
        String palin = new LongestPalindromicSubstring().longestPalindrome("qwqwqwq");
        System.out.println(palin);
    }

}
