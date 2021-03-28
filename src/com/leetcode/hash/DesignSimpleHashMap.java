package com.leetcode.hash;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tangweiyang
 * @Description:
 * @date 2021/3/14 下午8:27
 */
public class DesignSimpleHashMap {
    public static void main(String[] args) {
        /**
         * ["MyHashMap","remove","put","put","put","put","put","put","get","put","put"]
         * [[],[2],[3,11],[4,13],[15,6],[6,15],[8,8],[11,0],[11],[1,10],[12,14]]
         */
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.remove(2);
        myHashMap.put(3,11);
//        myHashMap.put(4,13);
//        myHashMap.put(15,6);
//        myHashMap.put(6,15);
//        myHashMap.put(8,8);
        myHashMap.put(11,0);
        int res = myHashMap.get(11);
        System.out.println(res);
        myHashMap.put(11,2);
        int value = myHashMap.get(11);
        System.out.println(value);
    }
}

class MyHashMap {

    List<Entry>[] entryArray;
    int mapSize = 8;

    /** Initialize your data structure here. */
    public MyHashMap() {
        entryArray = new ArrayList[mapSize];
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int index = key % mapSize;
        List<Entry> list = entryArray[index];
        if (list == null) {
            list = new ArrayList<Entry>();
            list.add(new Entry(key, value));
            entryArray[index] = list;
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            Entry entry = list.get(i);
            if (entry.key == key && entry.value != value) {
                list.remove(i);
                list.add(new Entry(key, value));
                return;
            }
        }
        list.add(new Entry(key, value));
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int index = key % mapSize;
        int value = -1;
        List<Entry> list = entryArray[index];
        if (list == null) {
            return value;
        }
        for (int i = 0; i < list.size(); i++) {
            Entry entry = list.get(i);
            if (entry.key == key) {
                value = entry.value;
                break;
            }
        }
        return value;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int index = key % mapSize;
        List<Entry> list = entryArray[index];
        if (list == null) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            Entry entry = list.get(i);
            if (entry.key == key) {
                list.remove(i);
                break;
            }
        }
    }

    class Entry {
        int key;
        int value;

        public Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
