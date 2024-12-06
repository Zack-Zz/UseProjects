package com.github.zack.use.java.base.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author zack
 * @since 2024/12/5
 */
public class CustomInvocationHandler implements InvocationHandler {

    private Object target;

    public CustomInvocationHandler(Object target) {
        this.target = target;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Prepare do something before proxy execute");
        Object invoke = method.invoke(target, args);

        return invoke;
    }
}
