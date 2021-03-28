package com.leetcode.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tangweiyang
 * @Description: 凑硬币
 * @date 2021/3/2 下午10:33
 */
public class Coin {
    public static int minCoinCount(int amount, int[] coins) {
        Map<Integer, Integer> dpMap = new HashMap<>();
        // <amount,coinCount>
        dpMap.put(0, 0);
        while (true) {
            for (Integer currentAmount : dpMap.keySet()) {
                int currentAmountCount = dpMap.get(currentAmount);
                for (int coin : coins) {
                    int nextAmount = currentAmount + coin;
                    int nextAmountCount = dpMap.get(nextAmount) == null ? Integer.MAX_VALUE : dpMap.get(nextAmount);
                    dpMap.put(nextAmount, Math.min(nextAmountCount, currentAmountCount + 1));
                }
                if (currentAmount >= amount) {
                    return dpMap.get(currentAmount);
                }
            }
        }

    }

    public static int minCoinCountV2(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, 1, amount + 1, Integer.MAX_VALUE);
        Arrays.sort(coins);
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] = Math.min(dp[j - coins[i]] + 1, dp[j]);
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int amount = 2;
        int[] coins = new int[] { 1, 2147483647 };
        //        int minCoinCount = minCoinCount(amount, coins);
        int minCoinCount = minCoinCountV2(amount, coins);
        System.out.println(minCoinCount);
    }
}
