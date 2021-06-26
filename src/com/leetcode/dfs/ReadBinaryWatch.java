/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.leetcode.dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author tangweiyang
 * @description 读表
 * @date 2021/6/21 下午11:27
 **/
public class ReadBinaryWatch {

    public List<String> readBinaryWatch(int turnedOn) {
        List<Integer> list = new ArrayList<>();
        int[] scores = new int[]{1, 2, 4, 8, 16, 32, 1 * 60, 2 * 60, 4 * 60, 8 * 60};
        dfs(scores, 0, 0, turnedOn, 0, list);
        Set<String> set = new HashSet<>();
        for (Integer time : list) {
            if (time > 24 * 60) {
                continue;
            }
            set.add(parse(time));
        }
        return new ArrayList<String>(set);
    }

    void dfs(int[] scores, int idx, int n, int turnedOn, int time, List<Integer> list) {
        int len = scores.length;
        if (idx == len) {
            if (n == turnedOn) {
                list.add(time);
            }
            return;
        }
        dfs(scores, idx + 1, n, turnedOn, time, list);
        dfs(scores, idx + 1, n + 1, turnedOn, time + scores[idx], list);
    }

    String parse(int time) {
        int hour = time / 60;
        int min = time % 60;
        return hour + ":" + (min > 9 ? min : ("0" + min));
    }

    public static void main(String[] args) {
        new ReadBinaryWatch().readBinaryWatch(10);
    }
}
