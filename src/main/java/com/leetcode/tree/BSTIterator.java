package com.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author tangweiyang
 * @Description:
 * @date 2021/3/28下午9:23
 **/

public class BSTIterator {
    Queue<Integer> queue = new LinkedList<Integer>();

    public BSTIterator(TreeNode root) {
        inOrder(root);
    }

    public int next() {
        return queue.poll();
    }

    public boolean hasNext() {
        return !queue.isEmpty();
    }

    private void inOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        do {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            queue.add(root.val);
            root = root.right;
        } while (!stack.isEmpty() || root != null);
    }

    private static TreeNode buildBTree(Integer[] vals) {
        Queue<TreeNode> bTreeQueue = new LinkedList();
        if (vals == null || vals.length == 0) {
            return null;
        }
        int i = 0;
        TreeNode treeNode = new TreeNode(vals[i++]), head = treeNode;
        bTreeQueue.add(treeNode);
        while (!bTreeQueue.isEmpty() && i < vals.length) {
            treeNode = bTreeQueue.remove();
            if (i < vals.length && vals[i] != null) {
                treeNode.left = new TreeNode(vals[i]);
                bTreeQueue.add(treeNode.left);
            }
            i++;
            if (i < vals.length && vals[i] != null) {
                treeNode.right = new TreeNode(vals[i]);
                bTreeQueue.add(treeNode.right);
            }
            i++;
        }
        return head;
    }

    public static void main(String[] args) {
        TreeNode treeNode = buildBTree(new Integer[] { 7, 3, 15, 0, null, 9, 20 });
        new BSTIterator(treeNode).inOrder(treeNode);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
