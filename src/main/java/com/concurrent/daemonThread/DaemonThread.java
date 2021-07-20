/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.concurrent.daemonThread;

/**
 * @author tangweiyang
 * @description 守护线程
 * @date 2021/7/18 下午12:13
 **/
public class DaemonThread {

    /**
     * main线程先执行完，但子线程还在执行，仍有用户线程存活着，JVM不会退出
     * 如果将子线程设置为守护线程，main线程执行完毕后，JVM直接退出，不管子线程是否执行完毕
     *
     * @param args
     */
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println(System.currentTimeMillis());
            while (true) {

            }
        });
        // 将子线程设置为守护线程
        // thread.setDaemon(true);
        thread.start();
        System.out.println("end");
    }
}
