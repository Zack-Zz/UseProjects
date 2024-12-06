package com.github.zack.use.cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * @author zhouze
 * @date 2024/12/6
 */
public class CglibExample {

    public static void main(String[] args) {
        // 创建 Enhancer 对象
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TargetClass.class); // 设置目标类
        enhancer.setCallback(new MyMethodInterceptor()); // 设置回调

        // 创建代理对象
        TargetClass proxy = (TargetClass) enhancer.create();
        proxy.sayHello(); // 调用方法会被拦截

        String john = proxy.greet("John");
    }
}
