/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.nio.callback;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @author tangweiyang
 * @description futureCallback
 * @date 2021/7/7 下午11:26
 **/
public class FutureCallback {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            System.out.println("调取http1接口");
            Thread.sleep(10000);
            System.out.println("http1 response successful");
            return "http1 success";
        });
        FutureTask<String> futureTask2 = new FutureTask<>(() -> {
            System.out.println("调取http2接口");
            Thread.sleep(10000);
            System.out.println("http2 response successful");
            return "http2 success";
        });
        executorService.submit(futureTask);
        executorService.submit(futureTask2);
        if (futureTask.isDone() && futureTask2.isDone()){
            System.out.println("success");
            System.out.println(futureTask.get());
        }
        String s = futureTask.get();
        System.out.println(s);
        System.out.println("end finish");
        executorService.shutdown();
    }

}
