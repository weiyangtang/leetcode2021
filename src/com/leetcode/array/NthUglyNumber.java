package com.leetcode.array;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author tangweiyang
 * @Description:
 * @date 2021/4/11 下午8:43
 */
public class NthUglyNumber {
    public int nthUglyNumber(int n) {
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, 2, 3, 4, 5);
        int[] prime = new int[] { 2, 3, 4, 5 };
        while (list.size() < n) {
            for (int i = 0; i < 4; i++) {
                int size = list.size();
                for (int j = 0; j < size; j++) {
                    list.add(prime[i] * list.get(j));
                }
            }
            list = list.stream().distinct().collect(Collectors.toList());
        }
        list = list.stream().sorted().collect(Collectors.toList());
        return list.get(n - 1);
    }
}
