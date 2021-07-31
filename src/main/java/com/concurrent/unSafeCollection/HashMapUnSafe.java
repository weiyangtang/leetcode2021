package com.concurrent.unSafeCollection;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class HashMapUnSafe {
    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>();
        /**
         * java7在resize()时在并发情况下,头插法可能会导致死循环；table[i] == null时，多个线程并发put（）会导致数据覆盖；
         * java8 改进了resize()采用了尾插法不会导致死循环；table[i] == null时，多个线程并发put（）会导致数据覆盖；
         *        if (++size > threshold)
         *             resize();
         *  ++size; 非原子操作，size不准确了，resize()时机更晚了。
         */
        map.put(1,"d");
        map.get(1);
    }
}
