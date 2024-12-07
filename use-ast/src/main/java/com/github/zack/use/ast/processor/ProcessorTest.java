package com.github.zack.use.ast.processor;

import javax.annotation.processing.Processor;
import java.util.ServiceLoader;

/**
 * @author zack
 * @since 2024/12/7
 */
public class ProcessorTest {

    public static void main(String[] args) {
        ServiceLoader<Processor> loader = ServiceLoader.load(Processor.class);
        for (Processor processor : loader) {
            System.out.println("Found Processor: " + processor.getClass().getName());
        }
    }

}
