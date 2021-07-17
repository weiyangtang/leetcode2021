package com.concurrent.theadLocal;

import java.util.Random;

/**
 * @author tangweiyang
 * @Description:
 * @date 2021/4/6 下午8:46
 */
public class ThreadLocalLearn {

    /**
     * 1. ThreadLocal如何能变量保证线程私有 回忆一下ThreadLocal中的变量是线程私有的原理，ThreadLocal.get() ->
     * currentThread.threadlocals -> getBy threadLocal * 变量是存储在当前线程的的threadLocals的Map中
     * <p>
     * 2. ThreadLocalMap.Entry.key 是WeakReference弱引用，为什么采用弱引用 - 只被弱引用关联的对象，只要遇到gc对象会被回收。
     * threadLocal强引用被释放了，threadLocal = null; 只被弱引用关联的TheadLocalMap.Entry.key会很快被GC回收，key = null,
     * 回收一部分内存，同时ThreadLocal在set/get时会根据清理部分key=null的value对象
     * <p>
     * 3. ThreadLocal什么情况下会造成OOM及原理 线程池中线程执行过程中threadLocalMap.set大对象,比如100M byte[],线程执行执行完后，线程并没有死亡，
     * threadLocalMap和Thread的生命周期是相同的，ThreadLocalMap大量的value对象没有并回收调
     * 4. threadLocal.remove(); // 会把value = null entry = null
     *
     * @param args
     */
    public static void main(String[] args) {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        threadLocal.set(1);
        ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();

        new Thread(() -> {
            threadLocal.set(1);
            stringThreadLocal.set("thread-1");
            for (int i = 0; i < 10; i++) {
                Integer integer = threadLocal.get();
                System.out.println(Thread.currentThread().getName() + "current value " + integer);
                System.out.println(Thread.currentThread().getName() + "current value " + stringThreadLocal.get());
                threadLocal.set(integer + 1);
                stringThreadLocal.set("thread-1-"+(integer + 1));
                try {
                    Thread.sleep(new Random().nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread-1").start();
        new Thread(() -> {
            threadLocal.set(1);
            stringThreadLocal.set("thread-2");
            for (int i = 0; i < 10; i++) {
                Integer integer = threadLocal.get();
                System.out.println(Thread.currentThread().getName() + "current value " + integer);
                System.out.println(Thread.currentThread().getName() + "current value " + stringThreadLocal.get());
                threadLocal.set(integer + 1);
                stringThreadLocal.set("thread-2-"+(integer + 1));
                try {
                    Thread.sleep(new Random().nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // threadLocal 在线程结束时，需要清除ThreadLocalMap中的entry.value。避免内存泄露。
                    // 尤其是线程池使用过程中，线程执行完后，并没有结束生命周期，而是继续返回到线程池中，
                    // 而Thread.ThreadLocalMap.Entry。value 因为是被强引用关联的对象，不会被GC回收
                    // 造成value对象值一致存活在JVM堆内存中，而JVM无法访问和回收，造成了内存泄露。
                    threadLocal.remove();
                }
            }
        }, "thread-2").start();
    }

}
