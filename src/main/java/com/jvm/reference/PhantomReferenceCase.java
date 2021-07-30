/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.jvm.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author tangweiyang
 * @description 虚引用
 * @date 2021/7/16 下午3:45
 **/
public class PhantomReferenceCase {

    /**
     * 虚引用，是无法通过get()方法获取引用的对象，源码直接返回null
     * 当发生GC，虚引用就会被回收，并且会把回收的通知放到ReferenceQueue中。
     * @param args
     */
    public static void main(String[] args) {
        ReferenceQueue queue = new ReferenceQueue();
        PhantomReference<byte[]> reference = new PhantomReference<byte[]>(new byte[1], queue);
        System.out.println(reference.get());
        System.out.println(queue.poll());
        List<byte[]> bytes = new ArrayList<>();
        new Thread(() -> {
            for (int i = 0; i < 100;i++ ) {
                bytes.add(new byte[1024 * 1024]);
            }
        }).start();

        new Thread(() -> {
            while (true) {
                Reference poll = queue.poll();
                if (poll != null) {
                    System.out.println("虚引用被回收了：" + poll);
                }
            }
        }).start();
        Scanner scanner = new Scanner(System.in);
        scanner.hasNext();
    }
}
