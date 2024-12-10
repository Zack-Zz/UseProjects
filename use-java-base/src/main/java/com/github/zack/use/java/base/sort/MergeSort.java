package com.github.zack.use.java.base.sort;

import java.util.Arrays;

/**
 * 归并排序
 *
 * @author zack
 * @since 2024/12/10
 */
public class MergeSort {
    public static void mergeSort(int[] array, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);
        merge(array, left, mid, right);
    }

    private static void merge(int[] array, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            temp[k++] = array[i] <= array[j] ? array[i++] : array[j++];
        }
        while (i <= mid) temp[k++] = array[i++];
        while (j <= right) temp[k++] = array[j++];
        System.arraycopy(temp, 0, array, left, temp.length);
    }

    public static void main(String[] args) {
        int[] array = {5, 2, 9, 1, 5, 6};
        mergeSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

}
