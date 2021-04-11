package com.leetcode.dp;

import java.util.HashMap;

/**
 * @author tangweiyang
 * @Description:
 * @date 2021/4/11 下午7:31
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 *
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 *
 * 示例 1:
 *
 * 输入: [3,2,3,null,3,null,1]
 *
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 *
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 * 示例 2:
 *
 * 输入: [3,4,5,1,3,null,1]
 *
 *      3
 *     / \
 *    4   5
 *   / \   \
 *  1   3   1
 *
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/house-robber-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 动态规划
 */
public class DaJiaJieSheIII {
    HashMap<TreeNode, Integer> hashMap = new HashMap<>();

    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = 0, right = 0;
        if (root.left != null) {
            if (root.left.left != null) {
                int val = 0;
                if (!hashMap.containsKey(root.left.left)) {
                    val = rob(root.left.left);
                    hashMap.put(root.left.left, val);
                }
                left += hashMap.get(root.left.left);
            }
            if (root.left.right != null) {
                int val = 0;
                if (!hashMap.containsKey(root.left.right)) {
                    val = rob(root.left.right);
                    hashMap.put(root.left.right, val);
                }
                left += hashMap.get(root.left.right);
            }
        }
        if (root.right != null) {
            if (root.right.left != null) {
                int val = 0;
                if (!hashMap.containsKey(root.right.left)) {
                    val = rob(root.right.left);
                    hashMap.put(root.right.left, val);
                }
                right += hashMap.get(root.right.left);
            }
            if (root.right.right != null) {
                if (!hashMap.containsKey(root.right.right)) {
                    int val = rob(root.right.right);
                    hashMap.put(root.right.right, val);
                }
                right += hashMap.get(root.right.right);
            }
        }
        int other = 0;
        if (root.left != null) {
            if (!hashMap.containsKey(root.left)) {
                int val = rob(root.left);
                hashMap.put(root.left, val);
            }
            other += hashMap.get(root.left);
        }
        if (root.right != null) {
            if (!hashMap.containsKey(root.right)) {
                int val = rob(root.right);
                hashMap.put(root.right, val);
            }
            other += hashMap.get(root.right);
        }

        return Math.max(root.val + left + right, other);
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
