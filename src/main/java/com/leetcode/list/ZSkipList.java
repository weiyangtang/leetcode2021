/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.leetcode.list;

/**
 * @author tangweiyang
 * @description 跳跃表
 * @date 2021/6/30 下午2:57
 **/
public class ZSkipList {

    ZSkipListNode header;

    ZSkipListNode tail;

    // 跳跃表的最大层
    int level;

    // 节点的个数，最底层的节点个数，最底层包含了所有的节点
    int length;

    ZSkipListNode p = new ZSkipListNode(Integer.MIN_VALUE, "start");
    ZSkipListNode q = new ZSkipListNode(Integer.MAX_VALUE, "end");

    public ZSkipList() {
        p.nextNode = q;
        q.prevNode = p;
        header = p;
        tail = q;
    }

    /**
     * 跳跃表查找
     * @param key
     * @return
     */
    public ZSkipListNode find(int key) {
        int currentLevel = 1;
        ZSkipListNode node;
        while (true) {
            node = header.nextNode;
            ZSkipListNode levelHeader = node;
            while (node != null && node.key < key) {
                node = node.nextNode;
            }
            if (node.key == key) {
                return node;
            }
            if (currentLevel <= level) {
                node = levelHeader.downNode;
                currentLevel++;
            }else {
                break;
            }
        }
        return null;

    }


}
