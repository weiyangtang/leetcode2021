package com.leetcode.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tangweiyang
 * @Description:
 * @date 2021/3/7 下午1:08
 */
public class PalindromePartitioning {
    public List<List<String>> partition(String s) {
        List<List<String>> resList = new ArrayList<>();
        List<String> currentList = new ArrayList<>();
        dfs(s, 1, "" + s.charAt(0), resList, currentList);
        return resList;
    }

    private void dfs(String s, int index, String pre, List<List<String>> resList, List<String> currentList) {
        if (index >= s.length()) {
            currentList.add(pre);
            resList.add(currentList);
            return;
        } else {
            List<String> list = new ArrayList();
            list.addAll(currentList);
            list.add(pre);
            dfs(s, index + 1, s.charAt(index) + "", resList, list);
            if (palin(pre + s.charAt(index))) {
                list = new ArrayList();
                list.addAll(currentList);
                dfs(s, index + 1, pre + s.charAt(index), resList, list);
            }
        }
    }

    private boolean palin(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        List<List<String>> list = new PalindromePartitioning().partition("aab");
    }
}
