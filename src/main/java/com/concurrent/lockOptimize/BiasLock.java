/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.concurrent.lockOptimize;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author tangweiyang
 * @description 如何引入了偏向锁
 * @date 2021/7/19 下午8:54
 **/
public class BiasLock {

    static ObjectLock o;

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000);
        o = new ObjectLock();
        System.out.println("hello");
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        // synchronized (o) {
        //     System.out.println(ClassLayout.parseInstance(o).toPrintable());
        // }
    }
    // static ObjectLock lock;
    //
    // public static void main(String[] args) throws Exception {
    //     Thread.sleep(5000);
    //     o = new ObjectLock();
    //     System.out.println("befre lock");
    //     System.out.println(ClassLayout.parseInstance(o).toPrintable());
    // }

}

class MyObject {

    String string = "d";
}

class ObjectLock {

    String name;
}
