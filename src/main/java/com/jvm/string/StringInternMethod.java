/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.jvm.string;


/**
 * @author tangweiyang
 * @description String intern
 * @date 2021/7/18 下午6:22
 **/
public class StringInternMethod {

    public static void main(String[] args) {
        String a = "a";
        String b = "b";
        String c = "ab";
        String d = a + b;
        String e = new String("ab");
        // 会返回一个String对象的引用，堆内存中一个对象的引用
        String f = "a" + "b";
        String g = new String("a") + new String("b");
        String h = new String("tnagweiyang");
        System.out.println(c.intern() == d);
        System.out.println(c.intern() == e);
        System.out.println(c == e.intern());
        System.out.println(c == f);
        System.out.println(e.intern() == e);
        g.intern();
        System.out.println(g == c);
        /**
         * 从下面两步操作还是能看出
         * intern()先在字符串常量池中比较字符串是否存在，不存在会拷贝一份到字符串常量池中，返回拷贝的变量引用值
         * 注意：Java7开始，字符串常量池被转移到堆内存了，intern()调用后，将字符串对象的引用记录一下*首次*在常量池中出现
         */
        System.out.println("h.intern() == h");
        System.out.println(h.intern() == h);
        System.out.println(h.intern() == "tnagweiyang");
    }
}
