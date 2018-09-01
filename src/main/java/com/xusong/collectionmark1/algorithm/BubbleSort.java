package com.xusong.collectionmark1.algorithm;


public class BubbleSort extends Sort {

    public static Integer[] sort(Integer[] arr) {
        arrayCheck(arr);
        if (arr.length == 1) {
            return arr;
        }
        // 最末位指针，由于每个周期会把最后一个元素定下来，那么，最末尾的指针是在每个周期结束后前移。
        for (int j = arr.length - 1; j >= 0; j--) {
            // 冒泡排序，每一步进行一次交换，本质上的目的，是把最小的元素扔到最后一个
            for (int i = 0; i < j; i++) {
                if (arr[i] < arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            }
        }
        return arr;
    }
}
