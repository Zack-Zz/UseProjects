package com.github.zack.use.java.base.sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author zack
 * @since 2024/12/10
 */
public class TimsortExample {

    public static void main(String[] args) {
        int[] array = {5, 2, 9, 1, 5, 6};
        Arrays.sort(array); // 使用Timsort
        System.out.println(Arrays.toString(array));

        List<Integer> list = Arrays.asList(5, 2, 9, 1, 5, 6);
        Collections.sort(list); // 使用Timsort
        System.out.println(list);
    }
}
