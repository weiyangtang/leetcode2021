package com.leetcode.stack;

/**
 * @author tangweiyang
 * @Description: 去除字符串相邻子串
 * @Link https://leetcode-cn.com/problems/remove-all-adjacent-duplicates-in-string/
 * @date 2021/3/9 下午11:01
 */
public class RemoveAllAdjacentDuplicatesInString {

    public String removeDuplicates(String S) {
        int size = S.length();
        StringBuilder sb = new StringBuilder();
        int len = 0;

        for(int i = 0; i < size; i++){
            if(len == 0){
                sb.append(S.charAt(i));
            }else {
                Character c = sb.charAt(len - 1);
                sb.deleteCharAt(--len);
                if(c != S.charAt(i)){
                    sb.append(c);
                    sb.append(S.charAt(i));
                    len = len + 2;
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        new RemoveAllAdjacentDuplicatesInString().removeDuplicates("abbaca");
    }
}
