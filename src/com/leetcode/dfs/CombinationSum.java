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
 * @description 组合总和
 * @team wuhan operational dev.
 * @date 2021/5/30 下午7:30
 **/
public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();
        dfs(list, new ArrayList<>(), 0, 0, candidates, target);
        return list;
    }

    private void dfs(List<List<Integer>> set, List<Integer> list, int index, int sum,
        int[] candidates, int target) {
        if (sum == target) {
            set.add(new ArrayList<>(list));
            return;
        } else if (sum > target) {
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            list.add(candidates[i]);
            dfs(set, list, i, sum + candidates[i], candidates, target);
            list.remove((Integer) candidates[i]);
        }
    }

    public static void main(String[] args) {
        new CombinationSum().combinationSum(new int[]{2, 3, 6, 7}, 7);
    }
}
