/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.leetcode.graph;

import java.util.LinkedList;
import java.util.Queue;

/***
 * @author tangweiyang
 * @description 传递消息
 * @date 2021/7/1 下午10:14
 * 小朋友 A 在和 ta 的小伙伴们玩传信息游戏，游戏规则如下：
 *
 * 有 n 名玩家，所有玩家编号分别为 0 ～ n-1，其中小朋友 A 的编号为 0
 * 每个玩家都有固定的若干个可传信息的其他玩家（也可能没有）。传信息的关系是单向的（比如 A 可以向 B 传信息，但 B 不能向 A 传信息）。
 * 每轮信息必须需要传递给另一个人，且信息可重复经过同一个人
 * 给定总玩家数 n，以及按 [玩家编号,对应可传递玩家编号] 关系组成的二维数组 relation。返回信息从小 A (编号 0 ) 经过 k 轮传递到编号为 n-1 的小伙伴处的方案数；若不能到达，返回 0。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/chuan-di-xin-xi
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class ChuanDiXiaoXi {

    /**
     * 我以为就是一道简单的图的dfs/bfs，没想到还可以做成dp
     * @param n
     * @param relation
     * @param k
     * @return
     */
    public static int numWays(int n, int[][] relation, int k) {
        int[][] graph = new int[n][n];
        for (int[] rows : relation) {
            graph[rows[0]][rows[1]] = 1;
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        int step = 0;
        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer current = queue.poll();
                if (step == k) {
                    res = current == n - 1 ? res + 1 : res;
                    continue;
                }
                for (int j = 0; j < n; j++) {
                    if (graph[current][j] == 1) {
                        queue.add(j);
                    }
                }
            }
            step++;
        }
        return res;
    }

    public static void main(String[] args) {
        int res = numWays(5, new int[][]{{0, 2}, {2, 1}, {3, 4}, {2, 3}, {1, 4}, {2, 0}, {0, 4}}, 3);
        System.out.println(res);
    }
}
