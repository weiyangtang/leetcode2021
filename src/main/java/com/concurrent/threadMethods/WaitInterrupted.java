/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.concurrent.threadMethods;

/**
 * @author tangweiyang
 * @description wait()方法 interrupted()中断返回
 * @date 2021/7/18 上午12:24
 **/
public class WaitInterrupted {

    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        Thread thread = new Thread(() -> {
            synchronized (o) {
                try {
                    o.wait();
                } catch (InterruptedException e) {
                    // e.printStackTrace();
                    System.out.println("线程中断了");
                }
            }
        });
        thread.start();
        Thread.sleep(100);
        thread.interrupt();
    }
}
