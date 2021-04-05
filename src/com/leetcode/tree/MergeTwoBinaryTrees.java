package com.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author tangweiyang
 * @Description: 合并二叉树
 * @link https://leetcode-cn.com/problems/merge-two-binary-trees/
 * @date 2021/4/1 上午12:34
 */
public class MergeTwoBinaryTrees {
    public static TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) {
            return root1 != null ? root1 : root2;
        }
        TreeNode left = mergeTrees(root1.left, root2.left);
        TreeNode right = mergeTrees(root1.right, root2.right);
        return new TreeNode(root1.val + root2.val, left, right);
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
        TreeNode root1 = createTree(new Integer[] { 1, 3, 2, 5 });
        TreeNode root2 = createTree(new Integer[] { 2, 1, 3, null, 4, null, 7 });
        TreeNode node = mergeTrees(root1, root2);
    }
}
