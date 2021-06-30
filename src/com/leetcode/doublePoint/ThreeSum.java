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
 * @description 三数之和
 * @date 2021/6/29 下午11:38
 **/
public class ThreeSum {

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int left = i + 1; left < n - 1; left++) {
                if (left > i + 1 && nums[left] == nums[left - 1]) {
                    continue;
                }
                int right = n - 1;
                while (nums[i] + nums[left] + nums[right] > 0 && left < right) {
                    right--;
                }
                if (nums[i] + nums[left] + nums[right] == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = threeSum(new int[]{1, -1, -1, 0});
        for (List<Integer> list : lists) {
            for (Integer integer : list) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }
}
