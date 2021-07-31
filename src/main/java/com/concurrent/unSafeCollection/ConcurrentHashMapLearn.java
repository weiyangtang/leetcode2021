package com.concurrent.unSafeCollection;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapLearn {
    public static void main(String[] args) {
        ConcurrentHashMap<Integer,Integer> concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put(1,1);
        concurrentHashMap.get(1);
    }
}
