/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.concurrent.threadMethods;

/**
 * @author tangweiyang
 * @description wait()方法
 * @date 2021/7/17 下午11:37
 **/
public class WaitMethod {

    public static void main(String[] args) {
        Object object = new Object();
        // monitorenter
        synchronized (object) {
            try {
                object.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //monitorexit
    }
}
/***
 * 代码反编译
 *
 * Compiled from "WaitMethod.java"
 * public class com.concurrent.threadMethods.WaitMethod {
 *   public com.concurrent.threadMethods.WaitMethod();
 *     Code:
 *        0: aload_0
 *        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *        4: return
 *
 *   public static void main(java.lang.String[]);
 *     Code:
 *        0: new           #2                  // class java/lang/Object
 *        3: dup
 *        4: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *        7: astore_1
 *        8: aload_1
 *        9: dup
 *       10: astore_2
 *       11: monitorenter
 *       12: aload_1
 *       13: invokevirtual #3                  // Method java/lang/Object.wait:()V
 *       16: goto          24
 *       19: astore_3
 *       20: aload_3
 *       21: invokevirtual #5                  // Method java/lang/InterruptedException.printStackTrace:()V
 *       24: aload_2
 *       25: monitorexit
 *       26: goto          36
 *       29: astore        4
 *       31: aload_2
 *       32: monitorexit
 *       33: aload         4
 *       35: athrow
 *       36: return
 *     Exception table:
 *        from    to  target type
 *           12    16    19   Class java/lang/InterruptedException
 *           12    26    29   any
 *           29    33    29   any
 * }
 */
