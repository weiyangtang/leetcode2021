/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.leetcode.tree;

import java.util.Arrays;

/**
 * @author tangweiyang
 * @description
 * @date 2021/6/6 上午9:57
 **/

public class RebuildBTree {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        int m = preorder.length;
        if (m == 1) {
            return new TreeNode(preorder[0]);
        }
        int rootIdx = 0;
        for (int i = 0; i < m; i++) {
            if (inorder[i] == preorder[0]) {
                rootIdx = i;
                break;
            }
        }
        TreeNode left = buildTree(
            Arrays.copyOfRange(preorder, 1, rootIdx + 1), Arrays.copyOfRange(inorder, 0, rootIdx));
        TreeNode right = buildTree(Arrays.copyOfRange(preorder, rootIdx + 1, m),
            Arrays.copyOfRange(inorder, rootIdx + 1, m));
        TreeNode root = new TreeNode(preorder[0]);
        root.left = left;
        root.right = right;
        return root;
    }

    public static void main(String[] args) {
        TreeNode node = new RebuildBTree()
            .buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});

    }
}
