package com.leetcode.sort;

import java.util.Arrays;

/**
 * 快排，20210731找工作刷题了
 */
public class QuickSort20210731 {
    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = divide(arr, left, right);
            quickSort(arr, left, mid - 1);
            quickSort(arr, mid + 1, right);
        }
    }

    public static int divide(int[] arr, int left, int right) {
        if (left == right) {
            return left;
        }
        int temp = arr[left];
        while (left < right) {
            while (arr[right] >= temp) {
                right--;
            }
            arr[left++] = arr[right];
            while (left < right && arr[left] < temp) {
                left++;
            }
            if (left < right) {
                arr[right--] = arr[left];
            }
        }
        arr[left] = temp;
        return left;
    }

    public static void main(String[] args){
        int[] arr = new int[]{2,4,1,6,3,7,8,9,3};
        quickSort(arr, 0 , arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
