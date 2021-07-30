/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.leetcode.sort;

import java.util.Arrays;

/**
 * @author tangweiyang
 * @description 归并排序
 * @date 2021/7/4 下午10:42
 **/
public class MergeSort {

    void sort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    void sort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        sort(array, left, mid);
        sort(array, mid + 1, right);
        merge(array, left, mid, right);
    }

    void merge(int[] array, int left, int mid, int right) {
        int[] arr1 = Arrays.copyOfRange(array, left, mid + 1);
        int[] arr2 = Arrays.copyOfRange(array, mid + 1, right + 1);
        int i = 0, j = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j]) {
                array[left++] = arr1[i++];
            } else {
                array[left++] = arr2[j++];
            }
        }
        while (i < arr1.length) {
            array[left++] = arr1[i++];
        }
        while (j < arr2.length) {
            array[left++] = arr2[j++];
        }
    }

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        int[] array = new int[]{4, 2, 1, 7, 8, 5, 4, 73, 22, 77};
        mergeSort.sort(array);
        for (int i : array) {
            System.out.print(i + " ");
        }
    }
}
