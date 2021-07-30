/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.leetcode.array;

/**
 * @author bjhl
 * @description 下一个字典序
 * @team wuhan operational dev.
 * @date 2021/5/28 上午9:27
 **/
public class NextDict {

    public static void nextPermutation(int[] nums) {
        int size = nums.length;
        int left = 0;
        for (int i = size - 1; i > 0; i--) {
            if (nums[i - 1] < nums[i]) {
                left = i;
                break;
            }
        }
        int i = left, j = size - 1;
        while (i < j) {
            int temp = nums[i];
            nums[i++] = nums[j];
            nums[j--] = temp;
        }
        if (left > 0) {
            int k = left;
            while (nums[k] < nums[left - 1]) {
                k++;
            }
            int temp = nums[k];
            nums[k] = nums[left - 1];
            nums[left - 1] = temp;
        }
        for (int num : nums) {
            System.out.print(num + "\t");
        }
    }



    public static void main(String[] args) {
        nextPermutation(new int[]{8});
    }


}
