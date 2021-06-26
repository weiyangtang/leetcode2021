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
 * @description 滑动谜题 https://leetcode-cn.com/problems/sliding-puzzle/
 * @date 2021/6/26 下午10:46
 *
 *
 * 在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示, 以及一块空缺用 0 来表示.
 *
 * 一次移动定义为选择 0 与一个相邻的数字（上下左右）进行交换.
 *
 * 最终当板 board 的结果是 [[1,2,3],[4,5,0]] 谜板被解开。
 *
 * 给出一个谜板的初始状态，
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sliding-puzzle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class SlidingPuzzle {

    int[][] neighbor = new int[][]{{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};

    public int slidingPuzzle(int[][] board) {
        StringBuilder init = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                init.append(board[i][j]);
            }
        }
        if ("123450".equals(init.toString())) {
            return 0;
        }
        // dfs(input, zeroPosition, 0);
        // 0 下一步可以移动的位置
        Queue<String> queue = new LinkedList<>();
        Set<String> visitSet = new HashSet<>();
        queue.add(init.toString());
        int count = 0;
        while (!queue.isEmpty()) {
            count++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                List<String> list = nextAdjust(current);
                for (String s : list) {
                    if (visitSet.contains(s)) {
                        continue;
                    }
                    if ("123450".equals(s)) {
                        return count;
                    }
                    visitSet.add(s);
                    queue.offer(s);
                }
            }
        }
        return -1;
    }

    /**
     * 获取0下一步可以移动的位置
     *
     * @param prev
     * @return
     */
    private List<String> nextAdjust(String prev) {
        List<String> list = new ArrayList<>();
        int zeroPosition = prev.indexOf('0');
        char[] array = prev.toCharArray();
        for (int i = 0; i < neighbor[zeroPosition].length; i++) {
            swap(array, zeroPosition, neighbor[zeroPosition][i]);
            list.add(new String(array));
            swap(array, zeroPosition, neighbor[zeroPosition][i]);
        }
        return list;
    }


    private void swap(char[] array, int x, int y) {
        char temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }

    public static void main(String[] args) {
        SlidingPuzzle slidingPuzzle = new SlidingPuzzle();
        int res = slidingPuzzle.slidingPuzzle(new int[][]{{3, 2, 4}, {1, 5, 0}});
        System.out.println(res);
    }
}
