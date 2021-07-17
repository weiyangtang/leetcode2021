/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.jvm.reference;

import java.lang.ref.SoftReference;

/**
 * @author tangweiyang
 * @description 软引用
 * @date 2021/7/16 下午2:54
 **/
public class SoftReferenceCase {

    /**
     * 软引用的对象，会在GC后仍然内存不足情况下（即将发生OOM异常），回收软引用关联的对象，如果第二次GC后仍然内存不足，则抛出OOM异常。
     * 适用于memory-sensitive 本地缓存，在JVM内存充足的情况下，不会对其关联的对象回收，软引用称之为"可有可无"。
     *
     * 如何用实验证明只被*软引用关联的对象回收只会在第一次GC回收内存不足，即将抛出OOM异常前，进行第二次GC,对软引用关联对象进行回收
     *
     * 1. 把最大堆内存设置一个较小的值 -Xmx20M
     * 2. 关联10M大小的对象
     * 3. System.gc();通知gc
     * 4. 判断软引用是否还活着
     * 5. 关联20M大小的对象
     * 6. 判断软引用是否还活着
     *
     * @param args
     */
    public static void main(String[] args) {
        SoftReference<byte[]> softReference = new SoftReference<>(new byte[10 * 1024 * 1024]);
        System.gc();
        System.out.println(softReference.get());
        byte[] bytes = new byte[10 * 1024 * 1024];
        System.gc();
        System.out.println(softReference.get());
    }
}
