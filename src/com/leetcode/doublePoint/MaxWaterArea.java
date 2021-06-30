/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.leetcode.doublePoint;

/**
 * @author tangweiyang
 * @description 盛最多水的容器
 * @date 2021/6/30 下午3:50
 **/
public class MaxWaterArea {

    public static int maxArea(int[] height) {
        int max = 0;
        int n = height.length;
        int left = 0, right = n - 1;

        while (left < right) {
            int temp = Math.min(height[left], height[right]) * (right - left);
            max = Math.max(temp, max);
            // 短板很短，长板再长也不会增加水桶里的水
            if (height[left] >= height[right]) {
                right--;
            } else {
                left++;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int max = maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7});
        System.out.println(max);
    }
}
