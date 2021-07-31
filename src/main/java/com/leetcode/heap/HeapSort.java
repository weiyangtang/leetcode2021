package com.leetcode.heap;

import java.util.Arrays;

/**
 * 堆排序
 */
public class HeapSort {
    public static void heapSort(int[] arr) {
        buildHeap(arr);
        for (int i = 0; i < arr.length - 1; i++) {
            int temp = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = arr[0];
            arr[0] = temp;
            downAdjust(arr, 0, arr.length - 2 - i);
        }
    }

    /**
     * 构造大顶堆
     *
     * @param arr
     * @param parentId
     * @param length
     */
    public static void downAdjust(int[] arr, int parentId, int length) {
        int temp = arr[parentId];
        int childId = 2 * parentId + 1;
        while (childId <= length) {
            if (childId + 1 <= length && arr[childId] < arr[childId + 1]) {
                childId++;
            }
            if (temp >= arr[childId]) {
                break;
            }
            arr[parentId] = arr[childId];
            parentId = childId;
            childId = 2 * parentId + 1;
        }
        arr[parentId] = temp;
    }

    public static void buildHeap(int[] arr) {
        for (int i = (arr.length - 2) / 2; i >= 0; i--) {
            downAdjust(arr, i, arr.length - 1);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{7, 1, 3, 10, 5, 2, 8, 9, 6};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
