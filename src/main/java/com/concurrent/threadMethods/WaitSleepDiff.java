/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.concurrent.threadMethods;

/**
 * @author tangweiyang
 * @description wait()和sleep()区别
 * @date 2021/7/18 上午1:17
 **/
public class WaitSleepDiff {

    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();
        new Thread(() -> {
            synchronized (object) {
                System.out.println("wait before");
                try {
                    /**
                     * Thread.sleep(2000); 情况下
                     * 打印出
                     * owait before
                     * wait end
                     * check lock
                     *----------
                     * object.wait(2000);情况下
                     *                     wait before
                     *                      check lock
                     *                      wait end
                     *
                     * -------
                     * 结论：wait(）方法会释放锁；Thread.sleep不会释放锁
                     *
                     *
                     */
                    object.wait(2000);
                    // Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("wait end");
            }
        }).start();
        Thread.sleep(1000);
        new Thread(() -> {
            synchronized (object) {
                System.out.println("check lock");
            }
        }).start();
    }
}
