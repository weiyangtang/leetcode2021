/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.leetcode.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author tangweiyang
 * @description 括号表达式分解
 * @date 2021/7/6 上午9:27
 **/
public class BracketsExpression {

    String findBracketInner(String s) {
        System.out.println(s);
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                stack.push(chars[i]);
                continue;
            } else if (chars[i] == ')') {
                stack.pollLast();
                continue;
            }
            if (!stack.isEmpty()) {
                sb.append(chars[i]);
            }
        }
        if (sb.length() > 2) {
            sb.deleteCharAt(sb.length() - 1).deleteCharAt(0);
        }
        return sb.toString();
    }

}
