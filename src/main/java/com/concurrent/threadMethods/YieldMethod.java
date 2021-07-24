/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.concurrent.threadMethods;

/**
 * @author tangweiyang
 * @description thread.yield() 主动让出Cpu
 * @date 2021/7/18 上午10:45
 **/
public class YieldMethod {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "yield before");
                Thread.yield();
                System.out.println(Thread.currentThread().getName() + "yield after");
            }).start();
        }
    }
}
