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
 * @description 剑指offer 两个栈实现队列
 * @date 2021/6/27 下午10:05
 **/
public class Stack2Queue {

    Deque<Integer> stack1;
    Deque<Integer> stack2;

    public Stack2Queue() {
        stack1 = new LinkedList<>();
        stack2 = new LinkedList<>();
    }

    public void appendTail(int value) {
        stack1.push(value);
    }

    public int deleteHead() {
        if (!stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.poll());
            }
        }
        return stack2.isEmpty() ? -1 : stack2.poll();
    }

}
