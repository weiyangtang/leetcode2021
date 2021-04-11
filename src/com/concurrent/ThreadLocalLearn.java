package com.concurrent;

import java.util.Random;

/**
 * @author tangweiyang
 * @Description:
 * @date 2021/4/6 下午8:46
 */
public class ThreadLocalLearn {
    public static void main(String[] args) {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        threadLocal.set(1);
        ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();

        new Thread(() -> {
            threadLocal.set(1);
            stringThreadLocal.set("thread-1");
            for (int i = 0; i < 10; i++) {
                Integer integer = threadLocal.get();
                System.out.println(Thread.currentThread().getName() + "current value " + integer);
                System.out.println(Thread.currentThread().getName() + "current value " + stringThreadLocal.get());
                threadLocal.set(integer + 1);
                stringThreadLocal.set("thread-1-"+(integer + 1));
                try {
                    Thread.sleep(new Random().nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread-1").start();
        new Thread(() -> {
            threadLocal.set(1);
            stringThreadLocal.set("thread-2");
            for (int i = 0; i < 10; i++) {
                Integer integer = threadLocal.get();
                System.out.println(Thread.currentThread().getName() + "current value " + integer);
                System.out.println(Thread.currentThread().getName() + "current value " + stringThreadLocal.get());
                threadLocal.set(integer + 1);
                stringThreadLocal.set("thread-2-"+(integer + 1));
                try {
                    Thread.sleep(new Random().nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread-2").start();
    }

}
