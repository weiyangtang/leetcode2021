/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.leetcode.list;

/**
 * @author tangweiyang
 * @description 跳跃表的节点
 * @date 2021/6/30 下午2:58
 **/
public class ZSkipListNode {

    Integer key;

    String value;

    // 跳跃表的层级
    int level;

    // 节点的上下左右节点
    ZSkipListNode nextNode;

    ZSkipListNode prevNode;

    ZSkipListNode upNode;

    ZSkipListNode downNode;

    public ZSkipListNode(Integer key, String value) {
        this.key = key;
        this.value = value;
    }
}
