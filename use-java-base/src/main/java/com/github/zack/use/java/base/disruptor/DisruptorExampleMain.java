package com.github.zack.use.java.base.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zack
 * @since 2024/12/13
 */
public class DisruptorExampleMain {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        int bufferSize = 8; // RingBuffer 大小

        // 创建 Disruptor
        Disruptor<Event> disruptor = new Disruptor<>(
                new EventFactoryImpl(),
                bufferSize,
                executor
        );

        // 设置事件处理器,并行消费，每个处理器单独一个线程
        disruptor.handleEventsWith(new EventHandlerImpl(), new EventHandlerTwoImpl());

        // 启动 Disruptor
        disruptor.start();

        // 发布事件
        RingBuffer<Event> ringBuffer = disruptor.getRingBuffer();
        for (int i = 0; i < 20; i++) {
            long sequence = ringBuffer.next(); // 获取序号
            try {
                Event event = ringBuffer.get(sequence);
                event.setValue((int) sequence); // 填充数据
            } finally {
                ringBuffer.publish(sequence); // 发布事件
            }
        }


        System.out.println("down");
        disruptor.shutdown();
        executor.shutdown();
    }
}
