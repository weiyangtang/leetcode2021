package com.leetcode.stack;

import java.util.Stack;

/**
 * @author tangweiyang
 * @Description:
 * @date 2021/3/11 下午11:44
 */
public class BasicCalculator2 {
    public int calculate(String s) {
        s = s.trim();
        int size = s.length();
        int temp = 0;
        Stack<Character> symbolStack = new Stack<>();
        Stack<Integer> numberStack = new Stack<>();

        for (int i = 0; i < size; i++) {
            Character c = s.charAt(i);
            if (c == ' ') {
                continue;
            } else if (Character.isDigit(c)) {
                temp = temp * 10 + (c - '0');
            } else {
                while (!symbolStack.isEmpty()) {
                    Character topSymbol = symbolStack.peek();
                    if (priority(topSymbol) >= priority(c)) {
                        Integer topNum = numberStack.pop();
                        topSymbol = symbolStack.pop();
                        temp = calc(topNum, temp, topSymbol);
                    }else {
                        break;
                    }
                }
                symbolStack.push(c);
                numberStack.push(temp);
                temp = 0;
            }
        }
        while (!symbolStack.isEmpty()) {
            Character preSymbol = symbolStack.pop();
            Integer preNum = numberStack.pop();
            temp = calc(preNum, temp, preSymbol);
        }
        return temp;
    }

    private int priority(Character c) {
        return c == '+' || c == '-' ? 0 : 1;
    }

    private int calc(int a, int b, Character symbol) {
        switch (symbol) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
        }
        return 0;
    }

    public static void main(String[] args) {
        int res = new BasicCalculator2().calculate("-21*3+42/2*3/5-4*2/3");
        System.out.println(res);
        System.out.println(-21*3+42/2*3/5-4*2/3);
    }
}
