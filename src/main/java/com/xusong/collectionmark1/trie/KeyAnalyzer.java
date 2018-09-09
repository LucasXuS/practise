package com.xusong.collectionmark1.trie;

import java.io.Serializable;
import java.util.Comparator;


/**
 * @Description 我们从bit的层面对某个集合，或者数组进行分析，写一些 通用函数
 */
public abstract class KeyAnalyzer<K> implements Comparator<K>, Serializable {

    // 以下这些都是bit索引，众所周知，集合和数组的合法标记为0或以上的所有，那么0以下的完全可以做非法字符标记
    // 空指针标记
    protected static final int NULL_BIT_KEY = -1;
    // 比较两个元素第一个不同的bit的索引，如果这两个相等，那么返回相等的标记
    protected static final int EQUAL_BIT_KEY = -2;
    // 超过范围的标记
    protected static final int OUT_OF_BOUNDS_BIT_KEY = -3;


    // 判断用的静态函数
    public static boolean isNullBitKey(final int bitIndex) {
        return bitIndex == NULL_BIT_KEY;
    }

    public static boolean isEqualBitKey(final int bitIndex) {
        return bitIndex == EQUAL_BIT_KEY;
    }

    public static boolean isOutOfBoundsBitKey(final int bitIndex) {
        return bitIndex == OUT_OF_BOUNDS_BIT_KEY;
    }

    public static boolean isValidBitIndex(final int bitIndex) {
        return bitIndex >= 0;
    }

    // 获取该集合或数组有多少个bits
    public abstract int lengthInBits(final K key);

    // 获取每个元素有多少个bits
    public abstract int bitsPerElement();

    // 判断位于bitIndex的bit是不是1
    public abstract boolean isBitSet(final K key, final int bitIndex, final int lengthInBits);

    // 判断prefix是不是  offsetInBits 所在的元素的前缀
    public abstract boolean isPrefix(K prefix, int offsetInBits, int lengthBits, K key);

    public abstract int bitIndex(final String key, final int offsetInBits, final int lengthInBits,
                                 final String other, final int otherOffsetInBits, final int otherLengthInBits);


    @Override
    @SuppressWarnings("unchecked")
    public int compare(K o1, K o2) {
        if (o1 == null) {
            return o2 == null ? 0 : -1;
        } else if (o2 == null) {
            return 1;
        }
        return ((Comparable<K>) o1).compareTo(o2);
    }

}
