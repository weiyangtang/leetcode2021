package com.concurrent.juc;

import java.util.concurrent.CountDownLatch;

/**
 * countdownLatch 计数器
 */
public class CountdownLatchLearn {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        countDownLatch.await();
        countDownLatch.countDown();
    }
}
