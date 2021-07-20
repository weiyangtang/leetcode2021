/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.jvm.string;


/**
 * @author tangweiyang
 * @description 字符串对象拼接 反编译后看看怎么执行的
 * @date 2021/7/18 下午7:08
 **/
public class StringAppend {

    /***
     * String a = "ab" + "cde";
     * String b = new String("ab") + new String("def");
     * 上面两行代码特别有意思，具体有趣在哪里，
     * -------------------------------------
     *  我们先反编译class字节码文件
     * String var1 = "abcde";
     * String var2 = new String("ab") + new String("def");
     * 在JVM编译时期已经将字符串常量直接拼接起来，形成 "abcde" 字符串并放入到字符串常量池
     * new String() 只在运行期分配内存空间
     * -------------------------------------
     * javap -c 反汇编查看字节码指令
     * Compiled from "StringAppend.java"
     * public class com.jvm.string.StringAppend {
     *   public com.jvm.string.StringAppend();
     *     Code:
     *        0: aload_0
     *        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
     *        4: return
     *
     *   public static void main(java.lang.String[]);
     *     Code:
     *        0: ldc           #2                  // String abcde
     *        2: astore_1
     *        3: new           #3                  // class java/lang/StringBuilder
     *        6: dup
     *        7: invokespecial #4                  // Method java/lang/StringBuilder."<init>":()V
     *       10: new           #5                  // class java/lang/String
     *       13: dup
     *       14: ldc           #6                  // String ab
     *       16: invokespecial #7                  // Method java/lang/String."<init>":(Ljava/lang/String;)V
     *       19: invokevirtual #8                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
     *       22: new           #5                  // class java/lang/String
     *       25: dup
     *       26: ldc           #9                  // String def
     *       28: invokespecial #7                  // Method java/lang/String."<init>":(Ljava/lang/String;)V
     *       31: invokevirtual #8                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
     *       34: invokevirtual #10                 // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
     *       37: astore_2
     *       38: return
     * }
     * 1. 两个字符串常量是直接拼接的，"ab" + "cde";
     * 2. String b = new String("ab") + new String("def");
     * =====
     * 可以用下面的代码表示
     *         StringBuilder stringBuilder = new StringBuilder();
     *         String var1 = new String("ab");
     *         stringBuilder.append(var1);
     *         String var2 = new String("def");
     *         stringBuilder.append(var2);
     *         String var3 = stringBuilder.toString();
     * 3.  // 两个字符串变量是如何拼接的呢？
     *         String c = "ad";
     *         String d = "ef";
     *         String e = c + d;
     *
     *          * 反编译结果
     *          *      88: ldc           #11                 // String ad
     *          *       90: astore        7
     *          *       92: ldc           #12                 // String ef
     *          *       94: astore        8
     *          *       96: new           #3                  // class java/lang/StringBuilder
     *          *       99: dup
     *          *      100: invokespecial #4                  // Method java/lang/StringBuilder."<init>":()V
     *          *      103: aload         7
     *          *      105: invokevirtual #8                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
     *          *      108: aload         8
     *          *      110: invokevirtual #8                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
     *          *      113: invokevirtual #10                 // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
     *
     *StringBuilder var4 = new StringBuilder();
     *var4.append("ad");
     *var4.append("ef");
     *String var5 = var4.toString();
     *
     * 这个常用的字符串拼接是不是给我们很大的触动，
     * 比如 while(condition){
     *     a = b +d;
     *     // ....
     * }
     * 每个循环中不停的创建StringBuilder对象，append, 销毁对象，是不是非常慢啊，是的啊
     * 还不如我们自己创建一个StringBuilder对象
     *
     * @param args
     */
    public static void main(String[] args) {
        String a = "ab" + "cde";
        String b = new String("ab") + new String("def");
        // String b = new String("ab") + new String("def"); 在javap反汇编成字节码指令后，JVM使用了StringBuilder实现的
        StringBuilder stringBuilder = new StringBuilder();
        String var1 = new String("ab");
        stringBuilder.append(var1);
        String var2 = new String("def");
        stringBuilder.append(var2);
        String var3 = stringBuilder.toString();
        // 两个字符串变量是如何拼接的呢？
        String c = "ad";
        String d = "ef";
        String e = c + d;
        /***
         * 反编译结果
         *      88: ldc           #11                 // String ad
         *       90: astore        7
         *       92: ldc           #12                 // String ef
         *       94: astore        8
         *       96: new           #3                  // class java/lang/StringBuilder
         *       99: dup
         *      100: invokespecial #4                  // Method java/lang/StringBuilder."<init>":()V
         *      103: aload         7
         *      105: invokevirtual #8                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
         *      108: aload         8
         *      110: invokevirtual #8                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
         *      113: invokevirtual #10                 // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
         */
        StringBuilder var4 = new StringBuilder();
        var4.append("ad");
        var4.append("ef");
        String var5 = var4.toString();
        /**
         * 从
         */

    }
}
