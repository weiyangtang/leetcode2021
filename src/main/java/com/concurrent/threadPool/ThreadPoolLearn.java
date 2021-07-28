/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.concurrent.threadPool;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author tangweiyang
 * @description Java线程池学习
 * @date 2021/7/26 上午7:48
 **/
public class ThreadPoolLearn {

    public static void main(String[] args) {
        int corePoolSize = 2;
        int maximumPoolSize = 5;
        int keepAliveTimeOut = 10;
        BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>(5);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,
            maximumPoolSize, keepAliveTimeOut, TimeUnit.MILLISECONDS, blockingQueue);
        System.out.println("thread pool size " + threadPoolExecutor.getPoolSize());
        // 预先创建核心线程数
        // threadPoolExecutor.prestartAllCoreThreads();
        System.out.println("thread pool size " + threadPoolExecutor.getPoolSize());
        for (int i = 0; i < 20; i++) {
            // 线程池一次最多容纳maximumPoolSize + blockingQueueSize
            threadPoolExecutor.execute(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " thread start");
            });
        }
        threadPoolExecutor.shutdown();
        while (true) {
            if (threadPoolExecutor.isTerminated()) {
                System.out.println("活跃线程数" + threadPoolExecutor.getActiveCount());
                break;
            }
        }
    }
}
