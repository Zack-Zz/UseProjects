package com.github.zack.use.java.base.concurrent.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 *
 * @author zack
 * @since 2025/4/10
 */
public class ForkJoinPoolDemo {

    public static void main(String[] args) {
        long now = System.currentTimeMillis();

        ForkJoinPool pool = new ForkJoinPool();
        long result = pool.invoke(new SumTask(1, 1_000_000));

        System.out.println(result + " | " + (System.currentTimeMillis() - now) + "ms");
    }

    static class SumTask extends RecursiveTask<Long> {
        long start, end;

        SumTask(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if (end - start < 10000) {
                long sum = 0;
                for (long i = start; i <= end; i++) sum += i;
                return sum;
            }

            long mid = (start + end) / 2;
            SumTask left = new SumTask(start, mid);
            SumTask right = new SumTask(mid + 1, end);
            left.fork(); // 拆分

            System.out.println(Thread.currentThread().getName());
            return right.compute() + left.join(); // 合并
        }
    }

}
