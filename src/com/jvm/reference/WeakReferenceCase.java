/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.jvm.reference;

import java.lang.ref.WeakReference;

/**
 * @author tangweiyang
 * @description 弱引用
 * @date 2021/7/16 下午3:38
 **/
public class WeakReferenceCase {

    /**
     * 弱引用只要进行GC,不管内存空间是否充足，都会回收弱引用关联的对象
     *
     * 弱引用在很多地方都有用到，比如ThreadLocal、WeakHashMap。
     *
     * 好吧，这就是我来学JVM四种引用的目的
     *
     * @param args
     */
    public static void main(String[] args) {
        WeakReference<byte[]> weakReference = new WeakReference<>(new byte[10 * 1024 * 1024]);
        System.gc();
        System.out.println(weakReference.get());
    }
}
