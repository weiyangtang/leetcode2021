/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.leetcode.bfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/***
 * @author tangweiyang
 * @description 公交路线
 * @date 2021/6/28 下午11:05
 * 给你一个数组 routes ，表示一系列公交线路，其中每个 routes[i] 表示一条公交线路，第 i 辆公交车将会在上面循环行驶。
 *
 * 例如，路线 routes[0] = [1, 5, 7] 表示第 0 辆公交车会一直按序列 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... 这样的车站路线行驶。
 * 现在从 source 车站出发（初始时不在公交车上），要前往 target 车站。 期间仅可乘坐公交车。
 *
 * 求出 最少乘坐的公交车数量 。如果不可能到达终点车站，返回 -1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/bus-routes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class BusRoutes {

    public int numBusesToDestination(int[][] routes, int source, int target) {
        Map<Integer, List<Integer>> nextMap = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                int n = routes[i].length;
                List<Integer> list = nextMap.getOrDefault(routes[i][j], new ArrayList<>());
                for (int k = 1; k < n; k++) {
                    list.add(routes[i][(j + k + n) % n]);
                }
                nextMap.put(routes[i][j], list);
            }
        }
        Set<Integer> visitedSet = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer current = queue.poll();
                visitedSet.add(current);
                if (current.equals(target)) {
                    return step;
                }
                List<Integer> list = nextMap.getOrDefault(current, new ArrayList<>());
                for (Integer next : list) {
                    if (!visitedSet.contains(next)) {
                        queue.add(next);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] routes = new int[][]{{1, 2, 7}, {3, 6, 7}};
        BusRoutes busRoutes = new BusRoutes();
        int res = busRoutes.numBusesToDestination(routes, 1, 7);
        System.out.println(res);
    }

}
