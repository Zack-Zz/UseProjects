package com.github.zack.use.bytebuddy.aop;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * @author zack
 * @since 2024/12/6
 */
public class ByteBuddyAOPExample {

    public static void main(String[] args) throws Exception {
        // 动态代理目标类
        Class<?> proxyClass = new ByteBuddy()
                .subclass(UserService.class) // 创建 UserService 的子类
                .method(ElementMatchers.named("sayHello")) // 匹配方法 sayHello
                .intercept(MethodDelegation.to(LoggingInterceptor.class)) // 定义拦截逻辑
                .make()
                .load(UserService.class.getClassLoader()) // 加载到 JVM
                .getLoaded();

        // 创建代理实例并调用方法
        UserService userService = (UserService) proxyClass.getDeclaredConstructor().newInstance();
        String result = userService.sayHello("ByteBuddy");
        System.out.println("Result: " + result);
    }

}
