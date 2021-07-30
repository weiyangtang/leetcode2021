package com.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author tangweiyang
 * @Description:
 * @date 2021/4/11 下午6:04
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 *
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 *
 * 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,2,3,null,null,4,5]
 * 输出：[1,2,3,null,null,4,5]
 * 示例 2：
 *
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：root = [1]
 * 输出：[1]
 * 示例 4：
 *
 * 输入：root = [1,2]
 * 输出：[1,2]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SerializeDeserializeBinaryTree {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        String res = "";
        queue.add(root);
        while (!queue.isEmpty()) {
            root = queue.remove();
            if (root == null) {
                res += "null,";
            } else {
                res += root.val + "," ;
                queue.add(root.left);
                queue.add(root.right);
            }
        }
        return res.substring(0, res.length() - 1);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] vals = data.split(",");
        int size = vals.length;
        if (size == 0 || (size > 0 && "null".equals(vals[0]))) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode head = new TreeNode(Integer.parseInt(vals[0])), node = head;
        queue.add(node);
        int i = 1;
        while (i < size && !queue.isEmpty()) {
            node = queue.remove();
            TreeNode left, right;
            if (!"null".equals(vals[i])) {
                left = new TreeNode(Integer.parseInt(vals[i]));
                node.left = left;
                queue.add(left);
            }
            i++;
            if (i >= size) {
                break;
            }
            if (!"null".equals(vals[i])) {
                right = new TreeNode(Integer.parseInt(vals[i]));
                node.right = right;
                queue.add(right);
            }
            i++;
        }
        return head;
    }

    public static void main(String[] args) {
        SerializeDeserializeBinaryTree serializeDeserializeBinaryTree = new SerializeDeserializeBinaryTree();
        TreeNode root = serializeDeserializeBinaryTree.deserialize("1,2,3,null,null,4,5");
        String serialize = serializeDeserializeBinaryTree.serialize(root);
        System.out.println(serialize);
    }
}
