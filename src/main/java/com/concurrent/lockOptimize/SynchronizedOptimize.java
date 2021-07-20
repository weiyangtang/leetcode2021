/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.concurrent.lockOptimize;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author tangweiyang
 * @description 锁升级和优化
 * @date 2021/7/19 下午7:16
 **/
public class SynchronizedOptimize {

    public static void main(String[] args) {
        Object o = new Object();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            synchronized (o) {

            }
        }
        System.out.println("synchronized:" + (System.currentTimeMillis() - start));
        ReentrantLock reentrantLock = new ReentrantLock();
        start = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            reentrantLock.lock();
            reentrantLock.unlock();
        }
        System.out.println("reentrantLock:" + (System.currentTimeMillis() - start));

    }
}
