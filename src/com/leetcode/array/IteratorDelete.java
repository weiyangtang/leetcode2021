package com.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author tangweiyang
 * @Description:
 * @date 2021/3/5 下午9:55
 */
public class IteratorDelete {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(1, 2, 3, 4, 5));
//        for (int i = list.size()-1; i >= 0; i--) {
//            list.remove(i);
//        }
        for (Integer integer : list) {
            list.remove(integer);
        }
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        for (int i = 0; i < list.size(); i++) {
            list.remove(i);
        }
        System.out.println(list.size());
    }
}
