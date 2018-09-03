package com.xusong.collectionmark1.trie;

import java.io.Serializable;
import java.util.Comparator;

public abstract class KeyAnalyzer<K> implements Comparator<K>, Serializable {

    protected static final int NULL_BIT_KEY = -1;

    protected static final int EQUAL_BIT_KEY = -2;

    protected static final int OUT_OF_BOUNDS_BIT_KEY = -3;

    public static boolean isNullBitKey(final int bitIndex) {
        return bitIndex == NULL_BIT_KEY;
    }

    public static boolean isEqualBitKey(final int bitIndex) {
        return bitIndex == EQUAL_BIT_KEY;
    }

    public static boolean isOutOfBoundsBitKey(final int bitIndex) {
        return bitIndex == EQUAL_BIT_KEY;
    }

    public static boolean isValidBitIndex(final int bitIndex) {
        return bitIndex >= 0;
    }

    public abstract int lengthInBits(final K key);

    public abstract int bitsPerElement();

    public abstract boolean isBitSet(final K key, final int bitIndex, final int lengthInBits);

    @Override
    public int compare(K o1, K o2) {
        return 0;
    }

}
