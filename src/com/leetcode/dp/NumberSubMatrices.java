/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.leetcode.dp;

/**
 * @author bjhl
 * @description 子矩阵和
 * @team wuhan operational dev.
 * @date 2021/5/29 下午12:39
 **/
public class NumberSubMatrices {

    public static int numSubmatrixSumTarget(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] matrixSum = new int[m + 1][n + 1];
        for (int x = 1; x <= m; x++) {
            for (int y = 1; y <= n; y++) {
                matrixSum[x][y] =
                    matrixSum[x][y - 1] + matrixSum[x - 1][y] + matrix[x - 1][y - 1] - matrixSum[x
                        - 1][y - 1];
            }
        }
        int count = 0;
        for (int x1 = 0; x1 < m; x1++) {
            for (int y1 = 0; y1 < n; y1++) {
                for (int x2 = x1 + 1; x2 <= m; x2++) {
                    for (int y2 = y1 + 1; y2 <= n; y2++) {
                        int sum = matrixSum[x2][y2] - matrixSum[x1][y2] - matrixSum[x2][y1]
                            + matrixSum[x1][y1];
                        if (sum == target) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    public static int numSubmatrixSumTargetDFS(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] matrixSum = new int[m + 1][n + 1];
        for (int x = 1; x <= m; x++) {
            for (int y = 1; y <= n; y++) {
                matrixSum[x][y] =
                    matrixSum[x][y - 1] + matrixSum[x - 1][y] + matrix[x - 1][y - 1] - matrixSum[x
                        - 1][y - 1];
            }
        }
        return dfs(m, n, matrixSum, target);
    }

    public static int dfs(int x2, int y2, int[][] matrixSum, int target) {
        int count = 0;
        for (int x1 = 0; x1 <= x2; x1++) {
            for (int y1 = 0; y1 <= y2; y1++) {
                if (x1 == x2 && y1 == y2) {
                    continue;
                }
                int sum = matrixSum[x2][y2] - matrixSum[x1][y2] - matrixSum[x2][y1]
                    + matrixSum[x1][y1];
                if (sum == target) {
                    count++;
                }
                count += dfs(x1, y1, matrixSum, target);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int count = numSubmatrixSumTarget(new int[][]{{0, 1, 0}, {1, 1, 1}, {0, 1, 0}}, 0);
        System.out.println(count);
        count = numSubmatrixSumTargetDFS(new int[][]{{0, 1, 0}, {1, 1, 1}, {0, 1, 0}}, 0);
        System.out.println(count);
    }
}


