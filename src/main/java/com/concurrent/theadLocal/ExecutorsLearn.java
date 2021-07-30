package com.concurrent.theadLocal;

import java.util.concurrent.Executors;

/**
 * 面试必问的Executors创建四种线程池
 * 1. newSingleThreadPoolExecutor()
 * 2. newFixedThreadPoolExecutor()
 * 3.newCacheThreadPoolExecutor()
 * 4. newScheduledThreadPoolExecutor()
 */
public class ExecutorsLearn {
    public static void main(String[] args) {
        /**
         *  return new FinalizableDelegatedExecutorService
         *             (new ThreadPoolExecutor(1, 1,
         *                                     0L, TimeUnit.MILLISECONDS,
         *                                     new LinkedBlockingQueue<Runnable>()));
         */
        Executors.newSingleThreadExecutor();
        /**
         *         return new ThreadPoolExecutor(nThreads, nThreads,
         *                                       0L, TimeUnit.MILLISECONDS,
         *                                       new LinkedBlockingQueue<Runnable>());
         */
        Executors.newFixedThreadPool(2);
        /**
         *         return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
         *                                       60L, TimeUnit.SECONDS,
         *                                       new SynchronousQueue<Runnable>());
         */
        Executors.newCachedThreadPool();
        /**
         *     public ScheduledThreadPoolExecutor(int corePoolSize) {
         *         super(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,
         *               new DelayedWorkQueue());
         *     }
         */
        Executors.newScheduledThreadPool(3);
        Executors.newWorkStealingPool();
    }
}
