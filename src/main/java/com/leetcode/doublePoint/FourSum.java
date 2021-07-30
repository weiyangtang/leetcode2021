/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.leetcode.doublePoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author tangweiyang
 * @description 四数之和
 * @date 2021/6/30 下午4:28
 **/
public class FourSum {

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                for (int k = j + 1; k < n - 1; k++) {
                    if (k > j + 1 && nums[k] == nums[k - 1]) {
                        continue;
                    }
                    int other = target - nums[i] - nums[j] - nums[k];
                    int right = n - 1;
                    while (nums[right] > other && right > k) {
                        right--;
                    }
                    if (other == nums[right] & right > k) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[k], nums[right]));
                    } else {
                        continue;
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = fourSum(new int[]{}, 2);
        for (List<Integer> list : lists) {
            for (Integer integer : list) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }

}
