/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.leetcode.hash;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author tangweiyang
 * @description 点菜展示表
 * @date 2021/7/6 下午10:51
 **/
public class DisplayTable {

    public List<List<String>> displayTable(List<List<String>> orders) {
        Map<Integer, Map<String, Integer>> maps = new HashMap<>();
        Set<String> foodSet = new HashSet<>();
        for (List<String> order : orders) {
            int tableNo = Integer.parseInt(order.get(1));
            Map<String, Integer> map = maps.getOrDefault(tableNo, new HashMap<>());
            String food = order.get(2);
            foodSet.add(food);
            int cnt = map.getOrDefault(food, 0) + 1;
            map.put(food, cnt);
            maps.put(tableNo, map);
        }
        List<String> foodList = new ArrayList<>(foodSet);
        Collections.sort(foodList, String::compareTo);
        List<List<String>> res = new ArrayList<>();
        List<String> header = new ArrayList<>();
        header.add("Table");
        for (String food : foodList) {
            header.add(food);
        }
        res.add(header);
        List<Map.Entry<Integer, Map<String, Integer>>> collect = maps.entrySet().stream()
            .sorted(Comparator.comparing(Map.Entry::getKey)).collect(
                Collectors.toList());
        for (Map.Entry<Integer, Map<String, Integer>> entry : collect) {
            List<String> list = new ArrayList<>();
            list.add(String.valueOf(entry.getKey()));
            for (String food : foodList) {
                list.add(String.valueOf(entry.getValue().getOrDefault(food, 0)));
            }
            res.add(list);
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
