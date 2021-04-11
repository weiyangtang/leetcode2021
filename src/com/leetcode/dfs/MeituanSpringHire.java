package com.leetcode.dfs;

/**
 * @author tangweiyang
 * @Description:
 * 美团2021春招 java方向第3道题
 *
 * 给一个长度为n的只包含0和1的序列，每次可以使用魔法消除相邻的3个数，在可以用任意次魔法的前提下，0的个数和1的个数的最大差值为多少?输出这个最大差值。
 *
 * 输入接述
 * 第一行是一个正和数n，表示序列的长度，第工行是只包含0和1的序列，其长度为n。
 *
 * 辆出接述
 * 轴出一个整数，表示这个最大差值。
 *
 * @思路：我使用回溯法 dfs
 * @date 2021/4/11 下午12:11
 */
public class MeituanSpringHire {
    public static int dfs(int i, int[] array, int max) {
        int size = array.length;
        if (i + 3 > size) {
            return max;
        }
        int[] temp = new int[] { array[i], array[i + 1], array[i + 2] };
        array[i] = array[i + 1] = array[i + 2] = 2;
        int abs = Math.abs(getNumberCount(array, 0) - getNumberCount(array, 1));
        max = Math.max(max, abs);
        for (int j = i + 1; j + 2 < size; j++) {
            max = dfs(j, array, max);
        }
        for (int j = i; j < i + 3; j++) {
            array[j] = temp[j - i];
        }
        return max;
    }

    private static int getNumberCount(int[] array, int number) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == number) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] array = new int[] { 0, 0, 0, 0, 1,0,1 };
        int max = Math.abs(getNumberCount(array, 0) - getNumberCount(array, 1));
        for (int i = 0; i + 2 < array.length; i++) {
           max =  dfs(i,array,0);
        }
        System.out.println(max);
    }
}
