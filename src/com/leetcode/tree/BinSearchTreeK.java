package com.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author tangweiyang
 * @Description: 二叉搜索树K值问题
 * @link https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/
 * @date 2021/4/1 上午1:21
 */
public class BinSearchTreeK {

    static int i = 1;

    public static int kthLargest(TreeNode root, int k) {
        if (root == null) {
            return 0;
        }
        int kthLargest = kthLargest(root.right, k);
        if (BinSearchTreeK.i > k) {
            return kthLargest;
        } else if (i == k) {
            return root.val;
        }
        BinSearchTreeK.i++;
       return kthLargest(root.left, k);
    }

    private static TreeNode createTree(Integer[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int size = nums.length;
        Queue<TreeNode> queue = new LinkedList<>();
        int i = 0;
        TreeNode node = new TreeNode(nums[i++]), root = node;
        queue.add(node);
        while (!queue.isEmpty()) {
            node = queue.remove();
            if (i < size && nums[i] != null) {
                node.left = new TreeNode(nums[i]);
                queue.add(node.left);
            }
            i++;
            if (i < size && nums[i] != null) {
                node.right = new TreeNode(nums[i]);
                queue.add(node.right);
            }
            i++;
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode root1 = createTree(new Integer[] { 3, 1, 4, null, 2 });
        int node = kthLargest(root1, 1);
    }
}
