/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.leetcode.sort;

/**
 * @author tangweiyang
 * @description 冒泡排序
 * @date 2021/7/1 上午11:29
 **/
public class BubbleSort {

    static void sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{2, 2, 1, 2, 9, 3, 2, 1, 8, 9, 6, 3, 64, 65, 32};
        sort(array);
        for (int i : array) {
            System.out.print(i + " ");
        }
    }
}
