/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.leetcode.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bjhl
 * @description  全排列
 * @date 2021/5/31 下午11:11
 **/
public class Permute {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        dfs(nums, 0, res, list);
        return res;
    }

    private void dfs(int[] nums, int idx, List<List<Integer>> res, List<Integer> list) {
        int n = nums.length;
        if (idx == n) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (list.contains(nums[i])) {
                continue;
            }
            list.add(nums[i]);
            dfs(nums, idx + 1, res, list);
            list.remove((Integer) nums[i]);
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> permute = new Permute().permute(new int[]{1, 2, 3});
    }
}
