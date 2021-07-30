package com.leetcode.array;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author tangweiyang
 * @Description: https://leetcode-cn.com/problems/132-pattern/
 * @date 2021/3/24 下午11:02
 */
public class L132Pattern {
    public static boolean find132pattern(int[] nums) {
        int len = nums.length;
        int[] leftMin = new int[len];
        int[] rightMin = new int[len];
        for(int i = 1; i < len; i++){
            if(i == 1){
                leftMin[i] = nums[i - 1];
                rightMin[len - i - 1] = nums[len -i];
            }else {
                leftMin[i] = Math.min(leftMin[i - 1], nums[i - 1]);
                rightMin[len - 1 - i] = Math.min(rightMin[len - i], nums[len - i]);
            }
        }
        for(int i = 1; i < len - 1; i++){
            if(nums[i] > leftMin[i] && nums[i] > rightMin[i] && leftMin[i] < rightMin[i]){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        find132pattern(new int[] { 3, 5, 0, 3, 4 });
    }
}
