package com.github.zack.use.java.base.sort;

import java.util.Arrays;

/**
 * 插入排序
 *
 * @author zack
 * @since 2024/12/10
 */
public class InsertionSort {

    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] array = {5, 2, 9, 1, 5, 6};
        insertionSort(array);
        System.out.println(Arrays.toString(array));
    }

}
