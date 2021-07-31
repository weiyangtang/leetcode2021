package com.leetcode.dfs;

/**
 * 01背包问题
 */
public class ZeroOnePackage {

    public static int dp(int[] val, int[] weight, int totalWeight) {
        int dp[][] = new int[val.length][totalWeight + 1];
        for (int i = 0; i < val.length; i++) {
            for (int j = 1; j <= totalWeight; j++) {
                if (j >= weight[i]) {
                    if (i == 0) {
                        dp[i][j] = val[i];
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j - weight[i]] + val[i], dp[i - 1][j]);
                    }
                }
            }
        }
        return dp[val.length - 1][totalWeight];
    }

    public static void main(String[] args) {
        int W = 10;
        int val[] = new int[]{20, 30, 35, 40, 50};
        int w[] = new int[]{3, 4, 3, 5, 5};
        int dp = dp(val, w, W);
        System.out.println(dp);
    }
}
