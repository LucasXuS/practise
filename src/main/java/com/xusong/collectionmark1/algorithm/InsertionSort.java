package com.xusong.collectionmark1.algorithm;

public class InsertionSort extends Sort {

    public static Integer[] sort(Integer[] arr) {
        arrayCheck(arr);
        if (arr.length == 1) {
            return arr;
        }
        for (int i = 1; i < arr.length; i++) {
            insert(arr, i);
        }
        return arr;
    }


    // 我们在这里设定boundary是后半段的第一个
    // 返回应该插入的位置
    private static int getInsertIndex(Integer[] arr, int boundary) {
        Integer target = arr[boundary];
        for (int i = 0; i < boundary; i++) {
            if (arr[i] > target) {
                return i;
            }
        }
        return -1;
    }

    private static void insert(Integer[] arr, int boundary) {
        int insertIndex = getInsertIndex(arr, boundary);
        if (insertIndex != -1) {
            for (int i = boundary; i > insertIndex; i--) {
                swap(arr, i, i - 1);
            }
        }
    }
}
