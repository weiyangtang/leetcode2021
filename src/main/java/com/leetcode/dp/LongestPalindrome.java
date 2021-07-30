/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.leetcode.dp;

/**
 * @author tangweiyang
 * @description 最长回文子串 https://leetcode-cn.com/problems/longest-palindromic-substring/
 * @date 2021/6/6 下午9:21
 **/
public class LongestPalindrome {

    public String longestPalindrome(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        int max = 0, left = 0, right = 0;
        for (int i = 1; i < len; i++) {
            for (int j = 0; i + j < len; j++) {
                dp[j][j] = 1;
                dp[j][j + i] = i == 1 && s.charAt(j) == s.charAt(j + i) ? 1 : 0;
                if (i > 1) {
                    dp[j][j + i] =
                        dp[j + 1][j + i - 1] == 1 && s.charAt(j) == s.charAt(j + i) ? 1 : 0;
                }
                if (dp[j][i + j] == 1 && i > max) {
                    max = i;
                    left = j;
                    right = j + i;
                }
            }
        }
        return s.substring(left, right + 1);
    }

    public static void main(String[] args) {
        String palindrome = new LongestPalindrome().longestPalindrome("aaaa");
        System.out.println(palindrome);
    }
}
