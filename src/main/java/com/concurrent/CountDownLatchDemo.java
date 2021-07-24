package com.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @author tangweiyang
 * @Description:
 * @date 2021/4/4 下午9:20
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        countDownLatch.countDown();
        countDownLatch.await();
        /**
         *  countDownLatch.await();
         *    sync.acquireSharedInterruptibly(1);
         *          if (tryAcquireShared(arg) < 0)
         *             doAcquireSharedInterruptibly(arg);
         *
         *            protected int tryAcquireShared(int acquires) {
         *             return (getState() == 0) ? 1 : -1;
         *         }
         *
         */

    }
}
