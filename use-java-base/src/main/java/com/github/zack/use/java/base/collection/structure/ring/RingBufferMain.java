package com.github.zack.use.java.base.collection.structure.ring;

/**
 * @author zack
 * @since 2024/12/13
 */
public class RingBufferMain {

    public static void main(String[] args) {
        RingBuffer<Integer> ringBuffer = new RingBuffer<>(3);
        ringBuffer.add(1);
        ringBuffer.add(2);
        ringBuffer.add(3);

        System.out.println(ringBuffer.remove()); // 输出 1
        ringBuffer.add(4);
        System.out.println(ringBuffer.remove()); // 输出 2
        System.out.println(ringBuffer.remove()); // 输出 3
        System.out.println(ringBuffer.remove()); // 输出 4
    }
}
