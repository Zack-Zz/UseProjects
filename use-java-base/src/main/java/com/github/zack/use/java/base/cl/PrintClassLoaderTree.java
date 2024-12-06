package com.github.zack.use.java.base.cl;

/**
 * @author zack
 * @since 2024/12/4
 */
public class PrintClassLoaderTree implements PrintClassLoader {

    public static void main(String[] args) {
        PrintClassLoaderTree pl = new PrintClassLoaderTree();

        ClassLoader classLoader = PrintClassLoaderTree.class.getClassLoader();
        pl.printCl(classLoader);

        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        pl.printCl(contextClassLoader);

        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        pl.printCl(systemClassLoader);

    }

    @Override
    public void printCl(ClassLoader classLoader) {

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
