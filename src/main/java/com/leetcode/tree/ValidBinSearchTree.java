package com.leetcode.tree;
//给定一个二叉树，判断其是否是一个有效的二叉搜索树。
//
// 假设一个二叉搜索树具有如下特征： 
//
// 
// 节点的左子树只包含小于当前节点的数。 
// 节点的右子树只包含大于当前节点的数。 
// 所有左子树和右子树自身必须也是二叉搜索树。 
// 
//
// 示例 1: 
//
// 输入:
//    2
//   / \
//  1   3
//输出: true
// 
//
// 示例 2: 
//
// 输入:
//    5
//   / \
//  1   4
//     / \
//    3   6
//输出: false
//解释: 输入为: [5,1,4,null,null,3,6]。
//     根节点的值为 5 ，但是其右子节点值为 4 。
// 
// Related Topics 树 深度优先搜索 递归 
// 👍 988 👎 0

import java.util.LinkedList;
import java.util.List;

/**
 * @author tangweiyang
 *
 * 中序遍历的结果是有序的
 */
public class ValidBinSearchTree {

    public boolean isValidBST(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        inOrder(root, list);
        for(int i = 0; i < list.size() - 1; i++){
            if (list.get(i) > list.get(i+1)){
                return false;
            }
        }
        return true;
    }

    private void inOrder(TreeNode root, List<Integer> list){
        if (root == null){
            return;
        }
        inOrder(root.left, list);
        list.add(root.val);
        inOrder(root.right, list);
    }
}


