/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.concurrent.threadMethods;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * @author tangweiyang
 * @description wait()等待被其他线程唤醒
 * @date 2021/7/18 上午12:03
 **/
public class WaitNotifyAll {

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        Integer MAX_SIZE = 10;
        Random random = new Random();
        Thread producer = new Thread(() -> {
            synchronized (queue) {
                while (true) {
                    while (queue.size() == MAX_SIZE) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int nextInt = random.nextInt(100);
                    queue.add(nextInt);
                    System.out.println("produce " + nextInt);
                    // 唤醒消费者进行消费
                    queue.notifyAll();
                }
            }
        });
        Thread consumer = new Thread(() -> {
            synchronized (queue) {
                while (true) {
                    while (queue.size() == 0) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Integer remove = queue.remove();
                    System.out.println("consume " + remove);
                    // 唤醒生产者进行生产
                    queue.notifyAll();
                }

            }
        });
        consumer.start();
        producer.start();
    }
}
