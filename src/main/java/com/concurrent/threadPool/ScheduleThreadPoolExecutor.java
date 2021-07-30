package com.concurrent.threadPool;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 任务调度的线程池
 */
public class ScheduleThreadPoolExecutor {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        /***
         *     public ScheduledThreadPoolExecutor(int corePoolSize) {
         *         super(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,
         *               new DelayedWorkQueue());
         *     }
         */
//        scheduledExecutorService.awaitTermination()

        scheduledExecutorService.schedule(() -> {
            System.out.println("scheduledExecutor running");
        }, 10, TimeUnit.SECONDS);

    }
}
