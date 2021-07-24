package com.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * @author tangweiyang
 * @Description: volatile 关键字，保证了变量操作的可见性和有序性（禁止重排序）
 * @date 2021/4/1 上午10:06
 */
public class VolatileConcurrent {

    volatile static int count = 0;

    /**
     * 指令重排序变量
     */
    private static boolean ready;
    private static int number;

    private static void volatileConcurrentUnsafe() throws InterruptedException {
        int threadCount = 100;
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        IntStream.range(0, threadCount).forEach(i -> {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    count++;
                }
                countDownLatch.countDown();
            }).start();
        });
        countDownLatch.await();
        System.out.println(count);
    }

    /**
     * 其他线程对共享变量的修改没有可见性
     */
    private static void noVisibility() {
        new Thread(() -> {
            System.out.println("thread" + ready);
            while (!ready) {
                Thread.yield();
                System.out.println("thread" + ready);
            }
            System.out.println(number);
        }).start();
    }

    public static void main(String[] args) throws InterruptedException {
        // 验证volatile 修饰变量在并发操作是线程不安全的
         volatileConcurrentUnsafe();

//        noVisibility();
//        System.out.println("main" + ready);
//        ready = true;
//        number = 100;
//        System.out.println("main" + ready);

    }
}
