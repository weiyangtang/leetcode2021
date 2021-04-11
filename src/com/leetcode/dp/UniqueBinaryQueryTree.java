package com.leetcode.dp;

/**
 * @author tangweiyang
 * @Description: 不同的二叉查找树的数量
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 *
 * 示例:
 *
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @思路： 理解不难，就是一开始没有思路。将一棵树的类型数量，拆分成左右子树类型的乘积，解决了重复子问题，
 * 构造二叉查找树的过程
 * 每次从（1，n）取出一个数m作为根节点，分别以（1,m-1）构建二叉查找树的左子树，（m+1,n）构建二叉查找树的右子树
 * 假设（1，n）中构造成二叉查找树的类型个数为f(n),每次先取m作为根节点 ,
 * 此时的以m为根节点的二叉查找树的个数可以拆分成左子树的类型的个数 f(m-1),右子树不同类型数为f(n-m)
 *
 * f(n) = sum(f(i-1)*f(n-i)) for i in range(1,n)
 * f(0) = 1;
 * f(1) = 0;
 * @date 2021/4/11 下午4:53
 */
public class UniqueBinaryQueryTree {

    public int numTrees(int n) {
        int[] fn = new int[n + 1];
        fn[0] = fn[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                fn[i] += fn[j - 1] * fn[i - j];
            }
        }
        return fn[n];
    }
}
