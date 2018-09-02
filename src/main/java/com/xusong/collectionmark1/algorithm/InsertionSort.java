package com.xusong.collectionmark1.algorithm;

public class InsertionSort extends Sort {

    public static Integer[] sort(Integer[] arr) {
        arrayCheck(arr);
        if (arr.length == 1) {
            return arr;
        }

        // 每次可以分为两部分，前面部分是认为是排好序的，boundary插入到前面
        // 前一部分，定下来，是元素个数，和元素内容定下来，而不是元素位置定下来。
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
        // 如果没找到，说明前面的元素都比目标元素小，意味着不需要插入操作。
        if (insertIndex != -1) {
            for (int i = boundary; i > insertIndex; i--) {
                swap(arr, i, i - 1);
            }
        }
    }
}
