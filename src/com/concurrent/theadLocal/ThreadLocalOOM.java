/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.concurrent.theadLocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author tangweiyang
 * @description ThreadLocal 造成oom
 * @date 2021/7/17 下午12:02
 **/
public class ThreadLocalOOM {

    /***
     * 单个线程执行多次任务，ThreadLocal在run方法即将结束时就被ThreadLocaLMap回收掉，
     * 同时要切断ThreadLocalMap的Entry.key强引用关联，只被弱引用关联。
     *
     * 内存泄露，最明显的特征就是JVM回收到的内存不足，甚至于抛出OOM
     * 让效果比较明显，堆内存设置的比较小，-Xmx = 30M
     *
     * @param args
     */
    public static void main(String[] args) {
        // 线程池 submit 好像有点问题啊，先改用execute
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                ThreadLocal<byte[]> threadLocal = new ThreadLocal<>();
                threadLocal.set(new byte[10 * 1024 * 1024]);
                System.out.println("threadLocal set");
                // 如果不及时remove 就OOM了
                threadLocal.remove();
            });
        }
        while (!executorService.isTerminated()) {

        }
        System.out.println("end finish");
    }
}
