package com.leetcode.array;

import java.util.Arrays;

/**
 * @author tangweiyang
 * @Description:
 * @date 2021/5/23 下午11:04
 */
public class MaximumXorWithAnElementFromArray {

    /**
     * 时间复杂度过高O(n2)
     * */
    public static int[] maximizeXor(int[] nums, int[][] queries) {
        Arrays.sort(nums);
        int numsLen = nums.length;
        int queriesLen = queries.length;
        int[] res = new int[queriesLen];
        for (int i = 0; i < queriesLen; i++) {
            int j = numsLen - 1;
            while (j >= 0 && nums[j] > queries[i][1]) {
                j--;
            }
            if (j < 0) {
                res[i] = -1;
                continue;
            }
            int max = 0;
            while (j >= 0) {
                max = Math.max(max, queries[i][0] ^ nums[j--]);
            }
            res[i] = max;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] ints = maximizeXor(new int[] { 0, 1, 2, 3, 4 }, new int[][] { { 3, 1 }, { 1, 3 }, { 5, 6 } });
        for (int anInt : ints) {
            System.out.print(anInt + ",");
        }
    }
}
