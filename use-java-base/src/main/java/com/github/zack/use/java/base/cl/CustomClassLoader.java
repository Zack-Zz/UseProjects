package com.github.zack.use.java.base.cl;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author zack
 * @since 2024/12/4
 */
public class CustomClassLoader extends ClassLoader {

    public CustomClassLoader(ClassLoader parent) {
        super(parent);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // 将类的全限定名转换为文件路径
        String fileName = name.replace('.', '/') + ".class";

        try {
            InputStream is;
            // 从指定路径读取字节码文件
//            is = Files.newInputStream(Paths.get("/path/to/classes/" + fileName));
            is = Files.newInputStream(Paths.get("/Users/zhouze/Documents/git-projects/UseProjects/use-java-base/target/classes/" + fileName));
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();


            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            byte[] classData = outputStream.toByteArray();
            is.close();

            // 将字节码转换为 Class 对象
            return defineClass(name, classData, 0, classData.length);
        } catch (IOException e) {
            throw new ClassNotFoundException("无法加载类：" + name, e);
        }
    }
}
