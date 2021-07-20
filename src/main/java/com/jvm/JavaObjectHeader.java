/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.jvm;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author tangweiyang
 * @description Java 对象头部打印
 * @date 2021/7/19 下午2:35
 **/
public class JavaObjectHeader {

    public static void main(String[] args) throws InterruptedException {
        MyObject o = new MyObject();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        System.out.println("hashcode:: "+Integer.toBinaryString(o.hashCode()));
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        Thread.sleep(5000);
        o = new MyObject();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
            System.out.println(Thread.currentThread().getId());
        }
    }
}

class MyObject{
    String string = "d";
}