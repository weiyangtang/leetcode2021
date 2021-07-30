/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.leetcode.dp;

/**
 * @author tangweiyang
 * @description 最后的石头
 * @date 2021/6/9 下午10:52
 **/
public class LastStoneWeightII {

    public static int lastStoneWeightII(int[] stones) {
        int len = stones.length;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += stones[i];
        }
        int capacity = sum / 2;
        int[][] dp = new int[len + 1][capacity + 1];
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= capacity; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= stones[i - 1]) {
                    dp[i][j] = Math.max(dp[i - 1][j - stones[i - 1]] + stones[i - 1], dp[i - 1][j]);
                }
            }
        }
        return sum - 2 * dp[len][capacity];
    }

    public static void main(String[] args) {
        lastStoneWeightII(new int[]{8, 5, 13, 9, 11});
    }
}
