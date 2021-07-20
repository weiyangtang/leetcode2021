/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.concurrent.deadLock;

/**
 * @author tangweiyang
 * @description 经典死锁
 * @date 2021/7/18 上午11:54
 **/
public class SimpleDeadLock {

    public static void main(String[] args) {
        Object object1 = new Object();
        Object object2 = new Object();
        Thread thread1 = new Thread(() -> {
            synchronized (object1) {
                // 保证了两个线程都获取到了第一个锁
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object2) {
                    System.out.println("线程A");
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            synchronized (object2) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object1) {
                    System.out.println("线程B");
                }
            }
        });
        thread1.start();
        thread2.start();
    }
}
