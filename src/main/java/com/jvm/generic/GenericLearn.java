package com.jvm.generic;

import java.util.ArrayList;
import java.util.Date;

/**
 * 泛型做类型擦除，先在编译期间做参数类型检查，字节码中还是以Object类型存储
 * 通过反射可以绕过编译期的泛型类型检查
 */
public class GenericLearn {

    public static void method(ArrayList<Date> list) {
        System.out.println("Arraylist<Date> list");
    }

    public static void main(String[] args) {

    }
}
