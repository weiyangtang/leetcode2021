package com.leetcode.tree;

/**
 * @author tangweiyang
 * @Description: 判断是否子结构树
 * @link https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/submissions/
 * @date 2021/4/5 下午11:55
 */
public class JudgeSubStruct {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if(A == null || B == null){
            return false;
        }
        return dfs(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    /**
     * 这个方法是单纯比较当前节点的
     */
    private boolean dfs(TreeNode A, TreeNode B){
        if(B == null){
            return true;
        }
        if(A == null || A.val != B.val){
            return false;
        }
        return dfs(A.left, B.left) && dfs(A.right, B.right);
    }
}
