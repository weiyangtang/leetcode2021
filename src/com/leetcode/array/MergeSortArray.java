package com.leetcode.array;

/**
 * @author tangweiyang
 * @Description: 合并有序数组
 * @link https://leetcode-cn.com/problems/merge-sorted-array/
 * @date 2021/4/5 下午10:31
 */
public class MergeSortArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] merge = new int[m + n];
        int i = 0, j = 0, k = 0;
        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) {
                merge[k++] = nums1[i++];
            } else {
                merge[k++] = nums2[j++];
            }
        }
        while (i < m) {
            merge[k++] = nums1[i++];
        }
        while (j < n) {
            merge[k++] = nums2[j++];
        }
        nums1 = merge;
        for (i = 0; i < m + n; i++) {
            nums1[i] = merge[i];
        }
    }

    public static void main(String[] args) {
        new MergeSortArray().merge(new int[] { 1, 2, 3, 0, 0, 0 }, 3, new int[] { 2, 5, 6 }, 3);
    }
}
