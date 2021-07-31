package com.leetcode.sort;

import java.util.Arrays;

/**
 * 希尔排序,本质就是利用直接插入排序在基本有序的情况下比较次数较少特性，通过将间隔d的数据分组排序，达到基本有序的目的。
 * 平均时间复杂度低于O(n2)
 */
public class ShellSort {
    public static void shellSort(int[] arr) {
        int d = arr.length / 2;
        // 希尔排序的分组d
        for (; d >= 1; d = d / 2) {
            // 每组的开始下标
            for (int j = 0; j < d; j++) {
                // 每组内部的排序
                for (int k = j + d; k < arr.length; k += d) {
                    int temp = arr[k];
                    int tmpIdx = k - d;
                    while (tmpIdx >= 0 && arr[tmpIdx] > temp) {
                        // 直接插入排序的有序区的后移
                        arr[tmpIdx + d] = arr[tmpIdx];
                        tmpIdx = tmpIdx - d;
                    }
                    arr[tmpIdx + d] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{6, 2, 8, 3, 4, 5, 6, 2, 1, 0, 9};
        shellSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
