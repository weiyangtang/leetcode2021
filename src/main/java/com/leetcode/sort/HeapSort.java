/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.leetcode.sort;

/**
 * @author tangweiyang
 * @description 堆排序
 * @date 2021/7/4 下午7:03
 **/
public class HeapSort {

    void sort(int[] array) {
        int len = array.length;
        // 调整为一个大顶堆
        for (int i = len / 2 - 1; i >= 0; i--) {
            adjustHeap(array, i, len);
        }
        // 每次从大顶堆第一个元素置换到最后一个
        for (int i = 0; i < len; i++) {
            swap(array, 0, len - 1 - i);
            // 对0~ len-1-i 之间的数字调整
            adjustHeap(array, 0, len - 1 - i);
        }
    }

    /**
     * 调整以i为根节点的子树是大顶堆
     *
     * @param array
     * @param i
     */
    void adjustHeap(int[] array, int i, int end) {
        int temp = array[i];
        for (int j = 2 * i + 1; j < end; j = 2 * j + 1) {
            // j 为左右节点最大的那个
            // 如果右节点大于左节点值，j为右节点
            if (j + 1 < end && array[j] < array[j + 1]) {
                j++;
            }
            if (array[j] > temp) {
                array[i] = array[j];
                i = j;
            } else {
                break;
            }
        }
        array[i] = temp;
    }

    void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public static void main(String[] args) {
        HeapSort heapSort = new HeapSort();
        int[] array = new int[]{4, 2, 1, 7, 8, 5, 4, 73, 22, 77};
        heapSort.sort(array);
        for (int i : array) {
            System.out.print(i + " ");
        }
    }
}
