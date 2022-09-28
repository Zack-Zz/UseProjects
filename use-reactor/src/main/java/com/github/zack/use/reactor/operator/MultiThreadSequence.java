package com.github.zack.use.reactor.operator;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import reactor.core.publisher.FluxSink;

import java.util.stream.IntStream;

/**
 * @author zhouze
 * @date 2022/8/8
 */
public class MultiThreadSequence {

    /**
     * 两个线程执行 emitElements()
     *
     * @param elementsToEmit
     * @param sharedSink
     */
    public void completeEmitElements(Integer elementsToEmit, FluxSink<Integer> sharedSink) {
        Thread producingThread1 = new Thread(() -> emitElements(sharedSink, elementsToEmit / 2), "Thread_1");
        Thread producingThread2 = new Thread(() -> emitElements(sharedSink, elementsToEmit / 2), "Thread_2");

        producingThread1.start(); // Start to emit elements
        producingThread2.start();

        try {
            producingThread1.join(); // Wait that thread finishes
            producingThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        sharedSink.complete();
    }

    public void emitElements(FluxSink<Integer> sink, Integer count) {
        IntStream.range(1, count + 1).boxed().forEach(sink::next);
    }

}
