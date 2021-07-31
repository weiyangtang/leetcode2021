package com.leetcode.sort;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort20210731 {
    public static int[] mergeSort(int[] arr, int left, int right) {
        if (left == right) {
            return new int[]{arr[left]};
        }
        int mid = (left + right) / 2;
        int[] arrLeft = mergeSort(arr, left, mid);
        int[] arrRight = mergeSort(arr, mid + 1, right);
        return merge(arrLeft, arrRight);
    }

    public static int[] merge(int[] arr1, int[] arr2) {
        int[] arr = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] <= arr2[j]) {
                arr[k++] = arr1[i++];
            } else {
                arr[k++] = arr2[j++];
            }
        }
        while (i < arr1.length) {
            arr[k++] = arr1[i++];
        }
        while (j < arr2.length) {
            arr[k++] = arr2[j++];
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4,2,6,9,5,3,2,8,1};
        int[] sortedArr = mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(sortedArr));
    }
}
