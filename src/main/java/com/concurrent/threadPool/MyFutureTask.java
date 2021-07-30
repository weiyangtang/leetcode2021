package com.concurrent.threadPool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * 自定义设置一个FutureTask实现异步等待
 * implements RunnableFuture  实现Runnable 和 Future
 */
public class MyFutureTask<T> implements RunnableFuture {

    volatile int state;
    private static final int NEW = 0;
    private static final int COMPLETING = 1;
    private static final int NORMAL = 2;
    private static final int EXCEPTIONAL = 3;
    private static final int CANCELLED = 4;
    private static final int INTERRUPTING = 5;
    private static final int INTERRUPTED = 6;

    private volatile WaitNode waitNode;

    T res;


    Callable<T> callable;

    public MyFutureTask(Callable<T> callable) {
        this.callable = callable;
        this.state = NEW;
    }

    @Override
    public void run() {
        // 1. callable执行完毕后
        try {
            res = callable.call();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            completeFinish();
        }
    }

    private void completeFinish() {
        state = NORMAL;
        LockSupport.unpark(waitNode.thread);
    }

    static final class WaitNode {
        volatile Thread thread;
        volatile WaitNode next;
        WaitNode() { thread = Thread.currentThread(); }
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public T get() throws InterruptedException, ExecutionException {
        if (state != NORMAL && waitNode == null){
            waitNode = new WaitNode();
        }
        while (state != NORMAL) {
            LockSupport.park();
        }
        return res;
    }

    @Override
    public Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        FutureTask<Integer> futureTask = new FutureTask(() -> {
//            Thread.sleep(500000);
//            return 100;
//        });
//        new Thread(futureTask).start();
//        System.out.println("main start");
//        Integer res = futureTask.get();
//        System.out.println(res);
//
        MyFutureTask<Integer> myFutureTask = new MyFutureTask<>(() -> {
            System.out.println("thread inner running");
            Thread.sleep(5000);
            System.out.println("thread inner end");
            return 100;
        });
        long start = System.currentTimeMillis();
        new Thread(myFutureTask).start();
        System.out.println(start);
        System.out.println("second main start");
        int res = myFutureTask.get();
        System.out.println(res);
        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis() - start);
    }
}
