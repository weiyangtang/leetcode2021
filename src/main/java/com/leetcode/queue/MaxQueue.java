/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.leetcode.queue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author tangweiyang
 * @description 最大队列 https://leetcode-cn.com/problems/dui-lie-de-zui-da-zhi-lcof/
 * @date 2021/6/3 下午11:19
 **/
public class MaxQueue {

    Deque<Integer> queue = new LinkedList<>();
    Deque<Integer> maxQueue = new LinkedList<>();


    public MaxQueue() {

    }

    public int max_value() {
        if (maxQueue.size() == 0) {
            return -1;
        }
        return maxQueue.peekFirst();
    }

    public void push_back(int value) {
        while (!maxQueue.isEmpty() && maxQueue.peekLast() < value) {
            maxQueue.removeLast();
        }
        maxQueue.addLast(value);
        queue.addLast(value);
    }

    public int pop_front() {
        if (queue.isEmpty()) {
            return -1;
        }
        int left = queue.pollFirst();
        if (left == maxQueue.peekFirst()){
            maxQueue.pollFirst();
        }
        return left;
    }

}
