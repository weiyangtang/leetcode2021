package com.concurrent;

/**
 * @author tangweiyang
 * @Description: synchronized锁实现单例模式
 * @date 2021/4/6 下午8:10
 */
public class SynchronizedSingle {
    public static void main(String[] args) {
        Single.getInstance();
    }

}

class Single {
    /**
     * volatile 可以禁止 JVM 的指令重排，保证在多线程环境下也能正常运行。
      */
    volatile static Single single;

    private Single() {
    }

    public static Single getInstance() {
        // 双层校验锁
        if (single == null) {
            synchronized (Single.class) {
                if (single == null) {
                    single = new Single();
                }
            }
        }
        return single;
    }
}
