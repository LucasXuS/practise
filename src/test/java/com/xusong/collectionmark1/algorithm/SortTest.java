package com.xusong.collectionmark1.algorithm;

import org.junit.Test;

public class SortTest {

    @Test
    public void bubbleSortTest() {
        Integer[] arr = {5, 9, 4, 7, 2, 3, 1, 8, 6};
        BubbleSort.sort(arr);
        print(arr);
    }

    @Test
    public void selectionSortTest() {
        Integer[] arr = {5, 9, 4, 7, 2, 3, 1, 8, 6};
        SelectionSort.sort(arr);
        print(arr);
    }

    private void print(Integer[] arr){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < arr.length; i++) {
            stringBuilder.append(arr[i]);
            if (i < arr.length - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
        System.out.println(stringBuilder.toString());
    }
}
