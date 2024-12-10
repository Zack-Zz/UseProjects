package com.github.zack.use.java.base.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author zack
 * @since 2024/12/10
 */
public class BubbleSort {

    public static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {5, 2, 9, 1, 5, 6};
        bubbleSort(array);
        System.out.println(Arrays.toString(array));
    }

}
