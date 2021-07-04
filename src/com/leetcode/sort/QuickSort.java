/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.leetcode.sort;

/**
 * @author tangweiyang
 * @description 快排
 * @date 2021/7/4 下午10:01
 **/
public class QuickSort {

    void sort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    void sort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = partition(array, left, right);
        sort(array, left, mid - 1);
        sort(array, mid + 1, right);
    }

    int partition(int[] array, int left, int right) {
        int m = array[left];
        while (left < right) {
            while (left < right && array[right] >= m) {
                right--;
            }
            array[left] = array[right];
            while (left < right && array[left] <= m) {
                left++;
            }
            array[right] = array[left];
        }
        array[left] = m;
        return left;
    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] array = new int[]{4, 2, 1, 7, 8, 5, 4, 73, 22, 77};
        quickSort.sort(array);
        for (int i : array) {
            System.out.print(i + " ");
        }
    }

}
