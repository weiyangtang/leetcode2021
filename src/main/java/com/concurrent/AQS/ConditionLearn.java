/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.concurrent.AQS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author tangweiyang
 * @description condition learn
 * @date 2021/7/26 上午11:36
 **/
public class ConditionLearn {

    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        int maxSize = 10;
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
        AtomicInteger atomicInteger = new AtomicInteger();
        Thread producer = new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(1000);
                    reentrantLock.lock();
                    if (queue.size() >= maxSize) {
                        condition.await();
                    }
                    queue.add(atomicInteger.incrementAndGet() + "");
                    System.out.println("produce: " + atomicInteger.get());
                    condition.signalAll();
                    reentrantLock.unlock();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Thread consumer = new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(2000);
                    reentrantLock.lock();
                    if (queue.size() == 0) {
                        condition.await();

                    }
                    String poll = queue.poll();
                    System.out.println("consumer: " + poll);
                    condition.signalAll();
                    reentrantLock.unlock();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        producer.start();
        consumer.start();
    }
}
