package com.xusong.collectionmark1.algorithm;

import com.xusong.collectionmark1.trie.analyzer.StringKeyAnalyzer;
import org.junit.Test;

public class SortTest {

    @Test
    public void bubbleSortTest() {
        Integer[] arr = {5, 9, 4, 7, 2, 3, 1, 8, 6};
        Integer[] arr1 = {1, 2};
        BubbleSort.sort(arr1);
        print(arr1);
    }

    @Test
    public void selectionSortTest() {
        Integer[] arr = {5, 9, 4, 7, 2, 3, 1, 8, 6};
        Integer[] arr1 = {1, 2};
        SelectionSort.sort(arr1);
        print(arr1);
    }

    @Test
    public void insertionSort() {
        Integer[] arr = {5, 9, 4, 7, 2, 3, 1, 8, 6};
        Integer[] arr1 = {1, 2};
        InsertionSort.sort(arr1);
        print(arr1);
    }

    private void print(Integer[] arr) {
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

    @Test
    public void stringAnalyzerIsBitSetTest() {
        String test = "hello";
        for (int i = 0; i < test.length(); i++) {
            System.out.println((Integer.toBinaryString((int) test.charAt(i))));
        }
        StringKeyAnalyzer analyzer = new StringKeyAnalyzer();
        boolean isBitSet = analyzer.isBitSet(test, 11, analyzer.lengthInBits(test));
        System.out.print(isBitSet);
    }
}
