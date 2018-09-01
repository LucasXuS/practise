package com.xusong.collectionmark1.algorithm;

public class SelectionSort extends Sort {

    public static Integer[] sort(Integer[] arr) {
        arrayCheck(arr);
        if (arr.length == 1) {
            return arr;
        }
        for (int i = 0; i < arr.length; i++) {
            int minIndex = getMinElementIndex(arr, i);
            swap(arr, i, minIndex);
        }
        return arr;
    }

    private static int getMinElementIndex(Integer[] arr, int fromIndex) {
        Integer min = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = fromIndex; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
}
