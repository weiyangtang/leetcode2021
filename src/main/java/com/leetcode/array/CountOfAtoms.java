/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.leetcode.array;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author tangweiyang
 * @description 原子数量
 * @date 2021/7/5 下午10:38
 *
 * 括号的求值错了
 **/
public class CountOfAtoms {

    public String countOfAtoms(String formula) {
        Map<String, Integer> map = find(formula);
        List<Map.Entry<String, Integer>> collect = map.entrySet().stream().sorted((o1, o2) -> {
            String key1 = o1.getKey();
            String key2 = o2.getKey();
            for (int i = 0; i < key1.length() && i < key2.length(); i++) {
                if (key1.charAt(i) > key2.charAt(i)) {
                    return 1;
                } else if (key1.charAt(i) < key2.charAt(i)) {
                    return -1;
                }
            }
            return key1.length() > key2.length() ? 1 : -1;
        }).collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();
        collect.forEach(entry -> {
            sb.append(entry.getKey());
            if (entry.getValue() == 1) {
                return;
            }
            sb.append(entry.getValue());
        });
        return sb.toString();
    }

    Map<String, Integer> find(String s) {
        Map<String, Integer> map = new HashMap<>();
        char[] array = s.toCharArray();
        int i = 0;
        StringBuilder sb = new StringBuilder();
        int count = 0;
        while (i < array.length) {
            // 遇到大写字母和（，将之前的汇总
            if ((Character.isUpperCase(array[i]) || array[i] == '(') && sb.length() > 0) {
                Integer defaultValue = map.getOrDefault(sb.toString(), 0);
                count = Math.max(count, 1);
                map.put(sb.toString(), defaultValue + count);
                sb = new StringBuilder();
                count = 0;
            }
            // 处理数字下标
            if (Character.isDigit(array[i])) {
                count = count * 10 + Integer.parseInt(array[i] + "");
            } else if (array[i] == '(') {
                // 处理括号内的
                int next = s.lastIndexOf(')');
                Map<String, Integer> innerMap = find(s.substring(i + 1, next));
                i = next + 1;
                while (i < s.length() && Character.isDigit(array[i])) {
                    count = count * 10 + Integer.parseInt(array[i] + "");
                    i++;
                }
                for (Map.Entry<String, Integer> entry : innerMap.entrySet()) {
                    Integer defaultValue = map.getOrDefault(entry.getKey(), 0);
                    map.put(entry.getKey(), defaultValue + entry.getValue() * count);
                }
            } else {
                // 普通字符
                sb.append(array[i]);
            }
            i++;
        }

        if (sb.length() > 0) {
            Integer defaultValue = map.getOrDefault(sb.toString(), 0);
            count = Math.max(count, 1);
            map.put(sb.toString(), Math.max(defaultValue + count, 1));
        }
        return map;
    }

    public static void main(String[] args) {
        String s = "HHe28Be26He";
        CountOfAtoms countOfAtoms = new CountOfAtoms();
        String res = countOfAtoms.countOfAtoms(s);
        System.out.println(res);
    }
}
