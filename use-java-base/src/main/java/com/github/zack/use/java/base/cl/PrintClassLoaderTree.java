package com.github.zack.use.java.base.cl;

/**
 * @author zack
 * @since 2024/12/4
 */
public class PrintClassLoaderTree {

    public static void main(String[] args) {
        ClassLoader classLoader = PrintClassLoaderTree.class.getClassLoader();
        printCl(classLoader);

        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        printCl(contextClassLoader);

        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        printCl(systemClassLoader);

    }

    public static void printCl(ClassLoader classLoader) {

        StringBuilder split = new StringBuilder("|--");
        boolean needContinue = true;
        while (needContinue) {
            System.out.println(split.toString() + classLoader);
            if (classLoader == null) {
                needContinue = false;
            } else {
                classLoader = classLoader.getParent();
                split.insert(0, "\t");
            }
        }
    }
}
