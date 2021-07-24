/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.concurrent.threadMethods;

/**
 * @author tangweiyang
 * @description thread.join() 当前线程等待该线程结束
 * @date 2021/7/18 上午10:51
 **/
public class JoinMethod {

    /**
     * 1. 获取thread的对象实例的监视器锁 2. 循环判断等待条件是否成立（线程是否死亡），调用wait()方法调用线程就会被等待挂起 3.
     * 线程死亡或者超时，不在循环调用wait()，此时只需要共享变量（这里的就是thread对象）调用notify()或者notifyAll()方法就能唤醒被调用线程 4.
     * 在Thread源码中并未找到有调用notifyAll地方，但join()源码注释写了在线程终结时，该线程对象实例调用notifyAll()
     * 唤醒所有被这个thread共享变量的wait()陷入等待状态的线程
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        // thread.join();
        // join 方法可以用下面两行代码进行代替
        synchronized (thread) {
            thread.wait();
        }
        System.out.println("main finish");
    }

}
