package com.github.zack.use.java.base.sort;

import java.util.Arrays;

/**
 * 选择排序
 *
 * @author zack
 * @since 2024/12/10
 */
public class SelectionSort {

    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
    }

    public static void main(String[] args) {
        int[] array = {5, 2, 9, 1, 5, 6};
        selectionSort(array);
        System.out.println(Arrays.toString(array));
    }
}
