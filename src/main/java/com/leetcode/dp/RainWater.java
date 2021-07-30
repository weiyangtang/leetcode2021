package com.leetcode.dp;

/**
 * @author tangweiyang
 * @Description:
 * @date 2021/3/6 下午11:33
 */
public class RainWater {
    public int trap(int[] height) {
        int size = height.length;
        int[] left = new int[size];
        int[] right = new int[size];
        for (int i = 1; i < size; i++) {
            left[i] = max(left[i - 1], height[i - 1]);
            right[size - 1 - i] = max(right[size - i], height[size - i]);
        }
        int waterSum = 0;
        for (int i = 1; i < size - 1; i++) {
            int singleWater = min(left[i], right[i]) - height[i];
            waterSum += singleWater > 0 ? singleWater : 0;
        }
        return waterSum;
    }

    private int min(int a, int b) {
        return a < b ? a : b;
    }

    private int max(int a, int b) {
        return a > b ? a : b;
    }

    public static void main(String[] args) {
        int[] height = new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
        int trap = new RainWater().trap(height);
        System.out.println(trap);
    }
}
