/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.concurrent.cas;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author tangweiyang
 * @description AtomicReference 可以针对 整个对象所有属性 CAS更新
 * @date 2021/7/20 上午11:25
 **/
public class AtomicReferenceCase {

    public static void main(String[] args) {
        Monitor monitor = new Monitor();
        monitor.lockNum = 1;
        monitor.monitorName = "monitor";
        AtomicReference<Monitor> atomicReference = new AtomicReference<>();
        Monitor newMonitor = new Monitor();
        monitor.lockNum = 10;
        monitor.monitorName = "slave";
        // 对Monitor对象整体更新
        atomicReference.compareAndSet(monitor, newMonitor);
        // 原子性更新了多个共享变量值
        System.out.println(monitor);

    }
}

class Monitor {
    String monitorName;
    Integer lockNum;

    @Override
    public String toString() {
        return "Monitor{" +
            "monitorName='" + monitorName + '\'' +
            ", lockNum=" + lockNum +
            '}';
    }
}
