package com.concurrent.AQS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author tangweiyang
 * @Description: AQS 原理学习
 * @link https://tech.meituan.com/2019/12/05/aqs-theory-and-apply.html
 * @date 2021/3/31 下午4:16
 */
public class AQSLearn {

    /****
     * 获取公平锁  static final class FairSync extends Sync，重写了 lock()方法 和 protected final boolean tryAcquire(int acquires)方法
     * new FairSync()
     *
     * reentrantLock.lock()
     *  1. acquire(1)
     *   1.1 调用 tryAcquire(1) 尝试获取锁
     *      1.1.1 getState(),获取临界资源的加锁次数，
     *       1.1.1.1 state == 0 如果等待队列中没有线程在等待获取锁并且CAS更新state值为1成功，设置 exclusiveOwnerThread = Thread.currentThread(); return true
     *       1.1.1.2 exclusiveOwnerThread = Thread.currentThread() 当前线程已拥有该独占锁，直接就是state+1，这个不用通过cas更新，更加高效。这个就实现了可重入的特性.return true
     *      1.1.1.3 return false; 获取锁失败
     *   1.2 acquireQueued(addWaiter(Node.EXCLUSIVE), arg))；在ryAcquire(1)获取锁失败，把当前线程放入到等待队列中，等待获取锁
     *      1.1.1 addWaiter(Node.EXCLUSIVE)，当前线程加入到等待队列中
     *         1.1.1.1 Node node = new Node(Thread.currentThread(), mode); 构造等待队列中的节点
     *         1.1.1.2 Node pred = tail; 判断队列的尾结点是否为空
     *             1.1.1.1.1 如果tail不为空。将node设置为队列的尾结点，return node
     *             1.1.1.1.2 如果tail为空，等待队列为空，调用 enq(node);
     *                 1.1.1.1.2.1 如若tail == null, head = tail = new Node(); 等待队列的head节点设置一个虚节点。然后将当前线程构建的node节点插入到队列中，return node;
     *      1.1.2 acquireQueued(final Node node, int arg) 从等待队列中获取独占式不可中断模式锁。下面是一个空循环
     *         1.1.2.1 final Node p = node.predecessor(); 获取当前线程插入到队列的节点的前一个节点
     *         1.1.2.2 如果 p == head （等待队列的head节点是虚节点，说明node节点就是事实的首节点），调用 tryAcquire(1)再次尝试获取锁成功
     *            1.1.2.2.1 设置head = node,消除node节点，获取锁是否失败标识 failed =false; return interrupted = false;
     *         1.1.2.3 说明p为头节点且当前没有获取到锁（可能是非公平锁被抢占了）或者是p不为头结点，
     *         这个时候就要判断当前node是否要被阻塞（被阻塞条件：前驱节点的waitStatus为-1），防止无限循环浪费资源。具体两个方法下面细细分析
     *           1.1.2.3.1 shouldParkAfterFailedAcquire(p, node) 判断当前node是否需要被阻塞
     *              1.1.2.3.1 node节点的前驱节点的waitStatus = signal (-1) 唤醒状态, 直接return true; 当前线程需要进行被阻塞，等待前面线程释放资源
     *              1.1.2.3.2 node节点的前驱节点 waitStatus = cancel (1) 取消状态，消除前驱节点中所有的取消状态的节点，直到前驱节点状态<0; return false; 不需要被阻塞
     *              1.1.2.3.2 node节点的前驱节点 waitStatus = 0  初始状态，cas更新前驱节点waitStatus = signal。不需要被阻塞
     *           1.1.12.3.2 parkAndCheckInterrupt() 当shouldParkAfterFailedAcquire()方法返回值为true,对当前线程进行阻塞 Thread.interrupted()
     *         1.1.2.4 如果没有获取到锁，标志位 failed = true时，cancelAcquire(node) 取消获取锁
     *           1.1.2.4.1 去除所有的取消状态的前驱节点
     *           1.1.2.4.2 当前node的waitStatus == 1 取消状态
     *           1.1.2.4.3 如果node是尾结点，尾结点设置为从尾部开始的最后一个waitStatus < 0的节点，如果设置成功了,同时尾结点设置为next为null.如果next节点不为空并且等待状态不为取消状态，则设置node前驱节点的后续节点为next
     *           1.1.2.4.4 如果当前节点是head的后继节点，或者上述条件不满足，那就唤醒当前节点的后继节点
     *
     *
     * reentrantLock.unlock();
     * 公平锁和非公平锁都是同一个方法
     *
     * 1.  sync.release(1);
     *  1.1 tryRelease(1)
     *     1.1.1 判断锁的独占线程是否当前线程
     *     1.1.2 getState() - releases; 加锁次数-1，如果加锁为0，则设置独占锁的拥有线程为null,return true ,释放锁了
     *     1.1.3 判断等待队列是否为空，如果不为空，激活等待队列中下一个节点
     *
     *
     *
     *
     */
    public static void main(String[] args) throws InterruptedException {
        // 默认构造器是非公平锁
        // ReentrantLock lock = new ReentrantLock();
        ReentrantLock lock = new ReentrantLock(true);
        lock.lock();
        // 不会阻塞，非公平锁tryAcquire
        lock.tryLock();
        lock.newCondition();
        //
        lock.tryLock(100, TimeUnit.SECONDS);

        try {
            if (lock.tryLock(100, TimeUnit.MILLISECONDS)) {

            }
        } finally {
            lock.unlock();
        }

    }
}
