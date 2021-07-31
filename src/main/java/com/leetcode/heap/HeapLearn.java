package com.leetcode.heap;

import java.util.Arrays;

/**
 * 构造最小堆
 */
public class HeapLearn {

    public static void downAdjust(int[] arr, int parentId, int length) {
        int temp = arr[parentId];
        int childId = 2 * parentId + 1;
        while (childId <= length) {
            // 右孩子小于左孩子，下个调整的就是右孩子
            if (childId + 1 <= length && arr[childId] > arr[childId + 1]) {
                childId++;
            }
            if (temp <= arr[childId]) {
                break;
            }
            arr[parentId] = arr[childId];
            parentId = childId;
            childId = 2 * parentId + 1;
        }
        arr[parentId] = temp;
    }

    public static void buildHeap(int[] arr) {
        int length = arr.length - 1;
        for (int i = (length - 2) / 2; i >= 0; i--) {
            downAdjust(arr, i, length);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{7, 1, 3, 10, 5, 2, 8, 9, 6};
        buildHeap(arr);
        System.out.println(Arrays.toString(arr));
    }
}
