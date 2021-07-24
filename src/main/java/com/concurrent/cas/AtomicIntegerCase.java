/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.concurrent.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author tangweiyang
 * @description 通过AtomicInteger 研究CAS
 * @date 2021/7/20 上午10:29
 **/
public class AtomicIntegerCase {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.incrementAndGet();
        /***
         *     public final int incrementAndGet() {
         *         return unsafe.getAndAddInt(this, valueOffset, 1) + 1;
         *     }
         *     调用unsafe类的getAndAddInt,参数分别atomicInteger的对象地址和value值的相对偏移量
         *            do {
         *             var5 = this.getIntVolatile(var1, var2);
         *         } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));
         *
         *         return var5;
         *       自旋compareAndSwap
         *       V 需要更新的值
         *       E 期望的值，期望内存的值是，也是老值
         *       N 新值
         *
         *       compareAndSwap 对应于一条系统指令，CPU级完成CAS操作,cpu层面的原子操作
         *       同时value 是volatile，保证了可见性和禁止指令重排
         *
         *       但仍然存在一些问题
         *       1. ABA 问题，一条线程把value: A->B->A, 对于B线程来说CAS过程没有感知到值的变化
         *       2. 存在一直自旋可能，解决思路是让JVM支持处理器提供的pause指令。 暂时无解
         *       3. 只能针对于一个共享变量进行CAS操作
         *             a. 加锁，保证临界区代码执行时原子操作
         *             b. 使用AtomicReference, 对象之间的原子性，把多个变量放到一个对象里面进行CAS操作。
         *             其他的juc.atomic下的原子类都是对基本数据类及其包装类进行的原子操作，比如Boolean Integer Long Double LongArray
         */
    }
}
