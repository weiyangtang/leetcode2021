package com.leetcode.tree;

/**
 * @author tangweiyang
 * @Description: 重构二叉树, 根据先序和中序遍历方式重建二叉树，一次过 nice
 * @date 2021/3/23 下午12:42
 */
public class RebuildBinaryTree {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return dfs(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode dfs(int[] preorder, int[] inorder, int preLeft, int prRight, int inLeft, int inRight) {
        if (preLeft > prRight || inLeft > inRight) {
            return null;
        }
        int i = inLeft;
        for (; i <= prRight && inorder[i] != preorder[preLeft]; i++) {

        }
        TreeNode root = new TreeNode(preorder[preLeft]);
        int leftNodeSize = i - inLeft;
        root.left = dfs(preorder, inorder, preLeft + 1, preLeft + leftNodeSize, inLeft, i - 1);
        root.right = dfs(preorder, inorder, preLeft + leftNodeSize + 1, prRight, i + 1, inRight);

        return root;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
