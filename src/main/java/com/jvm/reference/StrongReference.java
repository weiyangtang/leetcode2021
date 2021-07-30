/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.jvm.reference;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tangweiyang
 * @description 强引用
 * @date 2021/7/16 上午9:58
 **/
public class StrongReference {


    /**
     * 强引用，在任何时候都不会被GC回收，即使在内存不足的情况下，JVM宁愿抛出OOM异常。
     * 如果我们想让不再使用的强引用对象被回收，有什么办法吗？
     * 切断对象引用和对象之间联系
     *
     * Object obj = new Object();
     * obj = null;
     * 此时通知GC回收
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        // String str = new String("10003");
        // str = null;
        // System.gc();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 线程内的对象会随着线程的死亡而终结
        Thread thread = new Thread(() -> {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < 100000; i++) {
                list.add(i + "");
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        thread.join();
        System.out.println("线程结束了");
    }

}
