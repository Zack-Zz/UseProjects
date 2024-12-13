package com.github.zack.use.java.base.sharing;

/**
 * 测试缓存行填充与不填充的性能差异
 *
 * @author zack
 * @since 2024/12/13
 */
public class FalseSharingTest {

    public static void main(String[] args) throws InterruptedException {
        final int NUM_THREADS = 4;
        final int NUM_ITERATIONS = 1_000_000_000;

        // 使用填充的类
        PaddedSequence[] paddedSequences = new PaddedSequence[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            paddedSequences[i] = new PaddedSequence(0);
        }

        // 不使用填充的类
        UnPaddingSequence[] unPaddingSequences = new UnPaddingSequence[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            unPaddingSequences[i] = new UnPaddingSequence(0);
        }

        // 性能测试
        System.out.println("Testing without padding:");
        measurePerformance(NUM_THREADS, NUM_ITERATIONS, unPaddingSequences);

        System.out.println("Testing with padding:");
        measurePerformance(NUM_THREADS, NUM_ITERATIONS, paddedSequences);
    }

    private static void measurePerformance(int numThreads, int numIterations, Object[] sequences) throws InterruptedException {
        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            final int index = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < numIterations; j++) {
                    if (sequences[index] instanceof PaddedSequence) {
                        ((PaddedSequence) sequences[index]).set(j);
                    } else if (sequences[index] instanceof UnPaddingSequence) {
                        ((UnPaddingSequence) sequences[index]).set(j);
                    }
                }
            });
        }

        long startTime = System.nanoTime();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        long endTime = System.nanoTime();
        System.out.printf("Elapsed time: %.2f seconds%n", (endTime - startTime) / 1_000_000_000.0);
    }
}
