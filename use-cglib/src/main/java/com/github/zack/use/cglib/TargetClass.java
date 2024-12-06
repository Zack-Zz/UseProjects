package com.github.zack.use.cglib;

/**
 * @author zhouze
 * @date 2024/12/6
 */
public class TargetClass {

    public void sayHello() {
        System.out.println("Hello from TargetClass");
    }

    public String greet(String name) {
        String s = "Hello, " + name;
        System.out.println(s);
        return s;
    }
}
