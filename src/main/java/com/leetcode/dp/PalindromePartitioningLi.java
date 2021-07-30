package com.leetcode.dp;

/**
 * @author tangweiyang
 * @Description:
 * @date 2021/3/8 下午11:46
 */
public class PalindromePartitioningLi {
    public int minCut(String s) {
        int size = s.length();
        int[][] plain = new int[size][size];
        int[] dp = new int[size];
        for(int i = 0; i< size; i++){
            plain[i][i] = 1;
            dp[i] = 1000000;
        }
        // 构造一个对称回文矩阵 plain[i][j]==1 s[i:j]是回文的
        for(int i = 1; i< size; i++){
            for(int j = 0; j + i < size; j++){
                if(i == 1 && s.charAt(j) == s.charAt(j + i)){
                    plain[j][j + i] = 1;
                    plain[j + i][j] = 1;
                }else if(plain[j+1][j + i -1]==1 && s.charAt(j) == s.charAt(j + i)){
                    plain[j][j + i] = 1;
                    plain[j + i][j] = 1;
                }

            }
        }
        // 从字符串尾部开始，dp[j]表示 从s[j:size-1]最少有多少个回文子串
        for(int i = size - 1; i >=0 ; i--){
            for(int j = 0; j <= i; j++){
                if(plain[i][j] == 1){
                    if(i == size -1){
                        dp[j] = 1;
                    }else {
                        dp[j] = min(dp[j], dp[i+1] + 1);
                    }
                }
            }
        }
        return dp[0] -1 ;
    }

    private int min (int a, int b){
        return a < b ? a : b;
    }

    public static void main(String[] args) {
        int minCut = new PalindromePartitioningLi().minCut("aaabaa");
        System.out.println(minCut);
    }
}
