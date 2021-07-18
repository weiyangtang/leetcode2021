/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.concurrent.newThread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author tangweiyang
 * @description
 * @date 2021/7/17 下午11:28
 **/
public class CallableCase {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask(()->{
            System.out.println("callable");
            return "hello";
        });
        new Thread(futureTask).start();
        String s = futureTask.get();
        System.out.println(s);
    }
}
