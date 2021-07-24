package com.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author tangweiyang
 * @Description:
 * @date 2021/4/5 下午8:36
 */
public class CASLearn {

    AtomicInteger value = new AtomicInteger(0);
    static AtomicStampedReference<Integer> stampRef = new AtomicStampedReference(10, 1);

    private void casABA() {
        new Thread(() -> {
            try {
                int A = value.get();
                Thread.sleep(20);
                int B = 5;
                boolean casSuccess = value.compareAndSet(A, B);
                System.out.println(Thread.currentThread().getName() + "casSuccess" + casSuccess);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            int A = value.get();
            int B = 5;
            boolean casSuccess = value.compareAndSet(A, B);
            value.compareAndSet(B, A);
            System.out.println(Thread.currentThread().getName() + "casSuccess" + casSuccess);
        }).start();
    }

    /**
     * 利用版本号解决ABA问题
     */
    private void casABAStamp() {
        new Thread(() -> {
            int stamp = stampRef.getStamp();
            System.out.println(Thread.currentThread().getName() + " 第1次版本号： " + stamp);
            stampRef.compareAndSet(10, 11, stampRef.getStamp(), stampRef.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + " 第2次版本号： " + stampRef.getStamp());
            stampRef.compareAndSet(11, 10, stampRef.getStamp(), stampRef.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + " 第3次版本号： " + stampRef.getStamp());
        }, "张三").start();

        new Thread(() -> {
            try {
                int stamp = stampRef.getStamp();
                System.out.println(Thread.currentThread().getName() + " 第1次版本号： " + stamp);
                TimeUnit.SECONDS.sleep(2);
                boolean isSuccess = stampRef.compareAndSet(10, 12, stampRef.getStamp(), stampRef.getStamp() + 1);
                System.out.println(
                    Thread.currentThread().getName() + " 修改是否成功： " + isSuccess + " 当前版本 ：" + stampRef.getStamp());
                System.out.println(Thread.currentThread().getName() + " 当前实际值： " + stampRef.getReference());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "李四").start();
    }

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.incrementAndGet();
        /**
         * 会遇到ABA问题
         * 线程1对V修改 A -> B -> A
         * 线程2对V修改 A -> B
         * 线程中使用cas更新V中的值，expect = A, update = B,实际上V中值以及被更新了两次，但通过CAS方式无法感知的
         */
        new CASLearn().casABA();
        new CASLearn().casABAStamp();
    }
}
