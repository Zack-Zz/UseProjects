package com.github.zack.use.java.base.concurrent.lock;

import java.util.concurrent.locks.StampedLock;

/**
 *
 * @author zack
 * @since 2025/4/10
 */
public class StampedLockDemo {


    public static void main(String[] args) {
        Point point = new Point();

        point.move(1, 2);

        System.out.println(point.distanceFromOrigin());



    }


    public static class Point {
        private double x, y;
        private StampedLock stampedLock = new StampedLock();

        public double distanceFromOrigin() {
            long stamp = stampedLock.tryOptimisticRead();

            double cX = x;
            double cY = y;

            if (!stampedLock.validate(stamp)) {
                // 退回悲观读
                stamp = stampedLock.readLock();
                try {
                    cX = x;
                    cY = y;
                } finally {
                    stampedLock.unlockRead(stamp);
                }
            }
            return Math.sqrt(cX * cX + cY * cY);
        }


        public void move(double deltaX, double deltaY) {
            long stamp = stampedLock.writeLock(); // 写锁
            try {
                x += deltaX;
                y += deltaY;
            } finally {
                stampedLock.unlockWrite(stamp);
            }
        }

    }


}
