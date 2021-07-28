/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.concurrent.threadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author tangweiyang
 * @description ThreadPoolExecutor submit 方法
 * @date 2021/7/28 下午2:17
 **/
public class ThreadPoolExecutorSubmit {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int corePoolSize = 1;
        int maxPoolSize = 3;
        Long keepAlive = 10L;
        AtomicInteger atomicInteger = new AtomicInteger(0);
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(10);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        RejectedExecutionHandler abortPolicy = new ThreadPoolExecutor.AbortPolicy();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize,
            keepAlive, TimeUnit.SECONDS, blockingQueue, threadFactory, abortPolicy);
        Callable<Integer> call = () -> {
            Thread.sleep(2000);
            System.out.println(atomicInteger.get());
            return atomicInteger.incrementAndGet();
        };
        for (int i = 0; i < 10; i++) {
            executor.submit(call);
        }
        while (executor.getActiveCount() != 0){

        }
        System.out.println("thread pool executor shutdown");
        executor.shutdown();
    }

}
