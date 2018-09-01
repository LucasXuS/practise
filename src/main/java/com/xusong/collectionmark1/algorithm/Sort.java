package com.xusong.collectionmark1.algorithm;

public class Sort {

    protected static void swap(Integer[] arr, int index1, int index2) {
        Integer c = null;
        c = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = c;
    }

    protected static void arrayCheck(Integer[] arr){
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("array is null or empty.");
        }
    }
}
