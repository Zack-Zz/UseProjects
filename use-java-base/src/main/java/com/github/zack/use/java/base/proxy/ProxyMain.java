package com.github.zack.use.java.base.proxy;

import com.github.zack.use.java.base.cl.PrintClassLoader;
import com.github.zack.use.java.base.cl.PrintClassLoaderTree;

import java.lang.reflect.Proxy;

/**
 * @author zack
 * @since 2024/12/5
 */
public class ProxyMain {

    public static void main(String[] args) {
        ClassLoader classLoader = PrintClassLoaderTree.class.getClassLoader();

        CustomInvocationHandler invocationHandler = new CustomInvocationHandler(new PrintClassLoaderTree());

        PrintClassLoader printClassLoaderProxy = (PrintClassLoader) Proxy.newProxyInstance(classLoader, new Class[]{PrintClassLoader.class}, invocationHandler);

        printClassLoaderProxy.printCl(classLoader);
    }
}
