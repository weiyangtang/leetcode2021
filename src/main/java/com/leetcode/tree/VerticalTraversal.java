package com.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tangweiyang
 * @description 二叉树的垂序遍历
 * @create 2021/7/31 23:54
 */
public class VerticalTraversal {
    List<Entry> entrys = new ArrayList<>();

    public  List<List<Integer>> verticalTraversal(TreeNode root) {
        dfs(root, 0, 0);
        entrys.sort((o1, o2) -> {
            if (o1.y != o2.y) {
                return o1.y - o2.y;
            } else if (o1.x != o2.x) {
                return o1.x - o2.x;
            } else {
                return o1.val - o2.val;
            }
        });
        List<List<Integer>> res = new ArrayList<>();
        int i = Integer.MIN_VALUE;
        List<Integer> list = new ArrayList<>();
        for (Entry entry : entrys) {
            if (i != entry.y) {
                i = entry.y;
                if (list.size() > 0) {
                    res.add(list);
                    list = new ArrayList<>();
                }
            }
            if (list.size() > 0) {
                res.add(list);
            }
            list.add(entry.val);
        }
        return res;
    }

    private void dfs(TreeNode root, int x, int y) {
        if (root == null) {
            return;
        }
        entrys.add(new Entry(x, y, root.val));
        dfs(root.left, x + 1, y - 1);
        dfs(root.right, x + 1, y + 1);
    }

    class Entry {
        int x;
        int y;
        int val;

        Entry(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }

    public static void main(String[] args) {
        SerializeDeserializeBinaryTree serializeDeserializeBinaryTree = new SerializeDeserializeBinaryTree();
        TreeNode root = serializeDeserializeBinaryTree.deserialize("1,2,3,4,6,5,7");
        VerticalTraversal verticalTraversal = new VerticalTraversal();
        verticalTraversal.verticalTraversal(root);
    }
}


