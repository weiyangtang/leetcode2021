package com.leetcode.graph;

/**
 * @author tangweiyang
 * @Description: 可能的二分法
 * @link https://leetcode-cn.com/problems/possible-bipartition/
 * @date 2021/3/5 下午11:47
 */
public class PossibleBipartition {
    public boolean possibleBipartition(int N, int[][] dislikes) {
        int[][] matrix = new int[N + 1][N + 1];
        int[] group = new int[N + 1];
        for (int i = 0; i < dislikes.length; i++) {
            // 连接矩阵
            matrix[dislikes[i][0]][dislikes[i][1]] = 1;
            matrix[dislikes[i][1]][dislikes[i][0]] = 1;
        }
        for (int k = 1; k <= N; k++) {
            if (group[k] == 0) {
                group[k] = 1;
                if (!dfs(k, matrix, group, N)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean dfs(int i, int[][] matrix, int[] group, int N) {
        if (i > N) {
            return true;
        }
        for (int j = 1; j <= N; j++) {
            if (matrix[i][j] == 1 && group[j] == 0) {
                if (group[i] == group[j]) {
                    return false;
                } else if (group[j] == 0) {
                    group[j] = -group[i];
                    if (!dfs(j, matrix, group, N)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //    public static void main(String[] args) {
    //        int[][] matrix =
    //            new int[][] { { 0, 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0, 0 }, { 0, 1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 1, 1 },
    //                { 0, 0, 0, 1, 0, 1 }, { 0, 0, 0, 1, 1, 0 }, };
    //        Set<Set<Integer>> set = new HashSet<>();
    //        int N = 5;
    //        for (int i = 1; i <= N; i++) {
    //            Set<Integer> dfs = dfs(i, matrix, N);
    //            set.add(dfs);
    //        }
    //    }

    public static void main(String[] args) {
        //        int N = 5;
        //        int[][] dislikes = new int[][] { { 1, 2 }, { 3, 4 }, { 4, 5 }, { 3, 5 } };
        int N = 6;
        int[][] dislikes = new int[][] { { 1, 5 }, { 2, 3 }, { 3, 5 }, { 4, 6 } };
        boolean possibleBipartition = new PossibleBipartition().possibleBipartition(N, dislikes);
        System.out.println(possibleBipartition);
    }
}
