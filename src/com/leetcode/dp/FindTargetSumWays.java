/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.leetcode.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tangweiyang
 * @description 目标之和
 * @date 2021/6/7 下午11:34
 **/
public class FindTargetSumWays {


    public static int findTargetSumWays(int[] nums, int target) {
        int m = nums.length;
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            Map<Integer, Integer> subMap = new HashMap<>();
            if (i == 0) {
                int value = 1;
                if (nums[i] == 0) {
                    value = 2;
                }
                subMap.put(-nums[i], value);
                subMap.put(nums[i], value);
            } else {
                Map<Integer, Integer> preMap = map.get(i - 1);
                for (Map.Entry<Integer, Integer> entry : preMap.entrySet()) {
                    subMap.put(entry.getKey() - nums[i],
                        entry.getValue() + (subMap.containsKey(entry.getKey() - nums[i]) ? subMap
                            .get(entry.getKey() - nums[i]) : 0));
                    subMap.put(entry.getKey() + nums[i],
                        entry.getValue() + (subMap.containsKey(entry.getKey() + nums[i]) ? subMap
                            .get(entry.getKey() + nums[i]) : 0));
                }
            }
            map.put(i, subMap);
        }
        return map.get(m - 1).containsKey(target) ? map.get(m - 1).get(target) : 0;
    }


    /**
     * (sum - neg) - neg = target     neg = (sum - target)/2
     *
     * @param nums
     * @param target
     * @return
     */
    public static int findTargetSumWays2(int[] nums, int target) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        int diff = sum - target;
        if (diff < 0 || diff % 2 != 0) {
            return 0;
        }
        int neg = diff / 2;
        int[][] dp = new int[n + 1][neg + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= neg; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= nums[i - 1]) {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[n][neg];
    }


    public static void main(String[] args) {
        // int targetSumWays = findTargetSumWays(new int[]{0, 0, 2}, 2);
        // System.out.println(targetSumWays);

        int targetSumWays2 = findTargetSumWays2(new int[]{3, 2, 3}, 2);
        System.out.println(targetSumWays2);
    }
}
