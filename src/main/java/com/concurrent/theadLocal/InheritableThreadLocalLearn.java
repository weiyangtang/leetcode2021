/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.concurrent.theadLocal;

/**
 * @author tangweiyang
 * @description InheritableThreadLocal
 * @date 2021/7/17 下午12:54
 **/
public class InheritableThreadLocalLearn {

    /**
     * @param args
     */
    public static void main(String[] args) {
        /***
         * InheritableThreadLocalMap 继承于ThreadLocal
         * 重写了三个方法，重点的就是
         * crateMap() ->inheritableThreadLocals = new ThreadLocalMap()
         * getMap() -> return this.inheritableThreadLocals;
         * -----------------
         * InheritableThreadLocal 实现了子线程能够访问父线程的ThreadLocal变量
         *
         * InheritableThreadLocal 还是采用ThreadLocalMap.Entry<WeakReference<ThreadLocal>, Object>
         * 回忆一下ThreadLocal中的变量是线程私有的原理，ThreadLocal.get() -> currentThread.threadlocals -> getBy threadLocal
         * 变量是存储在当前线程的的threadLocals的Map中，InheritableThreadLocal原理类似，存储在当前线程的的inheritableThreadLocals的Map中
         * 为了能访问到父线程的变量，需要在创建子线程的时候，拷贝一份父线程的ThreadLocalMap， rehash到子线程的inheritableThreadLocals中
         *
         *         if (inheritThreadLocals && parent.inheritableThreadLocals != null)
         *             this.inheritableThreadLocals =
         *                 ThreadLocal.createInheritedMap(parent.inheritableThreadLocals);
         *          Stash the specified stack size in case the VM cares
         *      this.stackSize = stackSize;
         *
         *          Set thread ID
         *         tid = nextThreadID();
         *
         * 适用于一些中间件，用于记录下分布式链路的traceId；
         * 线程池获取父线程的traceId
         *}
         *
         */
        // ThreadLocal<String> threadLocal = new ThreadLocal<>();
        ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();
        threadLocal.set("hello");
        new Thread(() -> System.out.println(threadLocal.get())).start();
        threadLocal.set("3");
    }
}
