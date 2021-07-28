/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.concurrent.AQS;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author tangweiyang
 * @description ReentrantLock
 * @date 2021/7/24 下午11:07
 **/
public class ReentrantLockLearn {

    static int sum = 0;
    static ReentrantLock reentrantLock = new ReentrantLock();
    static CountDownLatch countDownLatch = new CountDownLatch(100);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            new MyThread().start();
        }
        countDownLatch.await();

        System.out.println(sum);

    }

    static class MyThread extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < 100000; i++) {
                /**
                 ReentrantLock 在lock()和unlock()之间的临界区资源具有可见性和有序性

                 其中标识加锁次数的变量volatile int state;

                 根据上面提到的volatile反编译多执行的lock指令，会使得当前Cache所有变量都刷新到主存中，同时无效化其他CPU或者内核的工作内存，保证了临界资源的可见性。

                 同时会在volatile变量的修改后设置一个内存屏障，保证了所有指令禁止重排序到内存屏障前。
                 */

                reentrantLock.lock();
                sum++;
                // System.out.println(sum++);
                reentrantLock.unlock();
            }
            countDownLatch.countDown();
            System.out.println("线程结束");
        }
    }
}
