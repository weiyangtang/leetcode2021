/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.leetcode.dp;

/**
 * @author tangweiyang
 * @description 一和零 https://leetcode-cn.com/problems/ones-and-zeroes/
 * @date 2021/6/6 下午3:38
 **/
public class FindMaxForm {

    public int findMaxForm(String[] strs, int m, int n) {
        int strLen = strs.length;
        int[][] strZeroOne = new int[strLen][2];
        for (int i = 0; i < strLen; i++) {
            for (int j = 0; j < strs[i].length(); j++) {
                strZeroOne[i][0] += strs[i].charAt(j) == '0' ? 1 : 0;
                strZeroOne[i][1] += strs[i].charAt(j) == '1' ? 1 : 0;
            }
        }
        int dp[][][] = new int[strLen + 1][m + 1][n + 1];
        for (int i = 1; i <= strLen; i++) {
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    dp[i][j][k] = dp[i - 1][j][k];
                    if (j >= strZeroOne[i - 1][0] && k >= strZeroOne[i - 1][1]) {
                        dp[i][j][k] = Math
                            .max(dp[i - 1][j - strZeroOne[i - 1][0]][k - strZeroOne[i - 1][1]] + 1,
                                dp[i - 1][j][k]);
                    }
                }
            }
        }
        return dp[strLen][m][n];
    }

    public static void main(String[] args) {
        int maxForm = new FindMaxForm()
            .findMaxForm(new String[]{"10", "0001", "111001", "1", "0"}, 5, 3);
        System.out.println(maxForm);
    }

}
