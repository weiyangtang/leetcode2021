/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.base;

/**
 * @author tangweiyang
 * @description 移位操作
 * @date 2021/6/29 下午5:35
 **/
public class ShiftOperate {

    static void leftShift() {
        int origin = 8;
        System.out.println(origin >> 2);
        System.out.println(origin << 1);
    }

    static String convertBinString(int num) {
        if (num == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            sb.append(num & 1);
            num = num >> 1;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        leftShift();
        int origin = 19930;
        System.out.println(convertBinString(origin));
        System.out.println(Integer.toBinaryString(origin));
        System.out.println(1 ^ 1);
        System.out.println(1 ^ 0);
        System.out.println(0 ^ 0);
    }
}
