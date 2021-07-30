package com.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author tangweiyang
 * @Description: 翻转二叉树
 * @link https://leetcode-cn.com/problems/invert-binary-tree/
 * @date 2021/3/31 下午11:33
 */
public class InvertBinTree {
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        swap(root);
        mirrorTree(root.left);
        mirrorTree(root.right);

        return root;
    }

    private void swap(TreeNode root) {
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }

    private TreeNode createTree(int[] nums) {
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
            if (i < size) {
                node.left = new TreeNode(nums[i++]);
                queue.add(node.left);
            }
            if (i < size) {
                node.right = new TreeNode(nums[i++]);
                queue.add(node.right);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        InvertBinTree invertBinTree = new InvertBinTree();
        TreeNode root = invertBinTree.createTree(new int[] { 4, 2, 7, 1, 3, 6, 9 });
        TreeNode node = invertBinTree.mirrorTree(root);
    }
}
