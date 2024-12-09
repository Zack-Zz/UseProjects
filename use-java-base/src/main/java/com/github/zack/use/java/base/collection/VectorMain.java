package com.github.zack.use.java.base.collection;

import java.util.Vector;

/**
 * @author zack
 * @since 2024/12/9
 */
public class VectorMain {

    public static void main(String[] args) {
        Vector<Long> vector = new Vector<>();

        vector.add(1L);

        Long[] arr = new Long[]{2L, 3L, 4L};
        vector.copyInto(arr);

        System.out.println(vector);

    }
}
