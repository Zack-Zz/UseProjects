package com.github.zack.use.java.base.collection;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author zack
 * @since 2024/12/9
 */
public class ArrayDequeMain {

    public static void main(String[] args) {
        Deque<String> stack = new ArrayDeque<>();
        stack.push("A");
        stack.push("B");
        stack.push("C");
        stack.push("B");
        System.out.println(stack.peek()); // 查看栈顶元素，不移除
        System.out.println(stack.pop());  // 弹出栈顶元素
    }
}
