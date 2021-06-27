/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.leetcode.bfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/***
 * @author tangweiyang
 * @description
 * @date 2021/6/27 下午4:47
 * N x N 的棋盘 board 上，按从 1 到 N*N 的数字给方格编号，编号 从左下角开始，每一行交替方向。
 *
 * 例如，一块 6 x 6 大小的棋盘，编号如下：
 *
 *
 * r 行 c 列的棋盘，按前述方法编号，棋盘格中可能存在 “蛇” 或 “梯子”；如果 board[r][c] != -1，那个蛇或梯子的目的地将会是 board[r][c]。
 *
 * 玩家从棋盘上的方格 1 （总是在最后一行、第一列）开始出发。
 *
 * 每一回合，玩家需要从当前方格 x 开始出发，按下述要求前进：
 *
 * 选定目标方格：选择从编号 x+1，x+2，x+3，x+4，x+5，或者 x+6 的方格中选出一个目标方格 s ，目标方格的编号 <= N*N。
 * 该选择模拟了掷骰子的情景，无论棋盘大小如何，你的目的地范围也只能处于区间 [x+1, x+6] 之间。
 * 传送玩家：如果目标方格 S 处存在蛇或梯子，那么玩家会传送到蛇或梯子的目的地。否则，玩家传送到目标方格 S。 
 * 注意，玩家在每回合的前进过程中最多只能爬过蛇或梯子一次：就算目的地是另一条蛇或梯子的起点，你也不会继续移动。
 *
 * 返回达到方格 N*N 所需的最少移动次数，如果不可能，则返回 -1。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/snakes-and-ladders
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class SnakesAndLadders {

    public int snakesAndLadders(int[][] board) {
        int N = board.length;
        int[] boardValue = new int[N * N];
        for (int i = 1; i < N * N; i++) {
            boardValue[i] = getBoardValue(i, board);
        }
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visitSet = new HashSet<>();
        queue.add(1);
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int idx = queue.poll();
                visitSet.add(idx);
                if (idx == N * N) {
                    return step;
                }
                int nextIdx = boardValue[idx];
                idx = nextIdx != -1 ? nextIdx : idx;
                if (idx == N * N) {
                    return step;
                }
                for (int k = 1; k <= 6 && idx + k <= N * N; k++) {
                    if (!visitSet.contains(k + idx)) {
                        queue.add(k + idx);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private List<Integer> getNeighbor(int prev, int[][] board) {
        int N = board.length;
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 6 && prev + i <= N * N; i++) {
            list.add(prev + i);
        }
        return list;
    }

    private int getBoardValue(int idx, int[][] board) {
        int N = board.length;
        int row = N - 1 - (idx - 1) / N;
        int col =
            (idx - 1) / N % 2 == 0 ? (idx % N == 0 ? N : idx % N) - 1
                : N - (idx % N == 0 ? N : idx % N);
        return board[row][col];
    }

    public static void main(String[] args) {
        SnakesAndLadders snakesAndLadders = new SnakesAndLadders();
        // int[][] board = new int[][]{{
        //     -1, -1, -1, -1, -1, -1
        // }, {
        //     -1, -1, -1, -1, -1, -1
        // }, {
        //     -1, -1, -1, -1, -1, -1
        // }, {
        //     -1, 35, -1, -1, 13, -1
        // }, {
        //     -1, -1, -1, -1, -1, -1
        // }, {
        //     -1, 15, -1, -1, -1, -1
        // }};
        int[][] board = new int[][]{{
            -1, -1, -1
        }, {
            -1, -1, -1
        }, {
            -1, -1, 9
        }};
        // int[][] board = new int[][]{{
        //     -1, -1
        // }, {
        //     -1, 3
        // }};
        int res = snakesAndLadders.snakesAndLadders(board);
        System.out.println(res);
    }
}
