package com.leetcode.tree;

import java.util.Stack;

/**
 * @author tangweiyang
 * @Description:
 * @date 2021/3/13 下午11:10
 */
public class Verify_preorder_serialization_of_a_binary_tree {
    public boolean isValidSerialization(String preorder) {
        String[] nodeVals = preorder.split(",");
        int size = nodeVals.length;
        Stack<Node> stack = new Stack<>();
        int i = 0;
        if (size == 0 || nodeVals[i].equals("#")) {
            return false;
        }
        Node node = new Node(nodeVals[i++]);
        stack.push(node);
        while (i < size) {
            while (!nodeVals[i].equals("#") && i < size) {
                node.left = new Node(nodeVals[i++]);
                node = node.left;
                stack.push(node);
            }
            i++;
            if (!stack.isEmpty() && i < size) {
                node = stack.pop();
                if (!nodeVals[i].equals("#")) {
                    node.right = new Node(nodeVals[i++]);
                    node = node.right;
                    stack.push(node);
                } else if (!stack.isEmpty()) {
                    node = stack.pop();
                }
                i++;
            } else {
                return false;
            }
        }
        if (!stack.isEmpty()) {
            return false;
        }
        return true;
    }

    class Node {
        String val;
        Node left;
        Node right;

        Node(String val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        boolean validSerialization = new Verify_preorder_serialization_of_a_binary_tree().isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#");
        System.out.println(validSerialization);
    }
}
