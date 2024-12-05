package com.github.zack.use.java.base.cl;

import java.lang.reflect.InvocationTargetException;

/**
 * @author zack
 * @since 2024/12/4
 */
public class CustomClassLoaderTester {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();

        CustomClassLoader customClassLoader = new CustomClassLoader(appClassLoader);

        Class<?> clazz = customClassLoader.findClass("com.github.zack.use.java.base.cl.PrintClassLoaderTree");

        Object o = clazz.getDeclaredConstructor().newInstance();
        clazz.getMethod("printCl", ClassLoader.class).invoke(o, clazz.getClassLoader());
    }
}
