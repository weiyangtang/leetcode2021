package com.concurrent.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author tangweiyang
 * @description 同步器，CyclicBarrier 回环屏障
 * @date 2021/7/29 23：58
 **/
public class CyclicBarrierLearn {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
            System.out.println("all thread start running together");
        });
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(() -> {
            System.out.println("step A finish");
            try {
                Thread.sleep(3000);
                System.out.println("step B finish: "+ System.currentTimeMillis());
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        executorService.execute(() -> {
            System.out.println("step A finish");
            try {
                Thread.sleep(5000);
                System.out.println("step B finish: "+ System.currentTimeMillis());
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        cyclicBarrier.await();
        System.out.println("main thread finish");
    }
}
