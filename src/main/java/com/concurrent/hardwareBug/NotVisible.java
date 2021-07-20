/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.concurrent.hardwareBug;

/**
 * @author tangweiyang
 * @description Java内存模型中的变量不可见问题
 * @date 2021/7/18 下午11:28
 **/
public class NotVisible {

    // 加volatile 就可以实现共享变量内存的可见性
    static boolean ready = false;


    /**
     * 下面是Java内存模型中工作内存不可见问题的实验
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.start();
        // 保证子线程已经running
        Thread.sleep(100);
        // main线程把 ready变量值拷贝到工作内存中，即使其他线程修改了工作内存变量值并同步到了主内存
        // main线程的工作内存中的ready值依旧没有更新
        while (!ready) {
            /**
             * 下面是两个坑
             * 1. System.out.println(ready); 是synchronized关键字修饰的方法，会清空当前线程的工作内存，重新从主内存获取变量值
             * 2. Thread.yield(); 虽然没有说yield有特殊的内存含义，但会影响变量可见性问题，cpu让出，工作内存都会清空？
             */
            // Thread.yield();
            // System.out.println(ready);
        }
        System.out.println(ready);
        System.out.println("main 线程结束了");
    }

    static class MyThread extends Thread {

        @Override
        public void run() {
            try {
                System.out.println("子线程" + ready);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ready = true;
            System.out.println("子线程结束");
        }
    }
}


