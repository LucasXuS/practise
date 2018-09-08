package com.xusong.collectionmark1.trie.analyzer;

import com.xusong.collectionmark1.trie.KeyAnalyzer;

public class StringKeyAnalyzer extends KeyAnalyzer<String> {

    // 每个元素的长度，这里我们的泛型具现化为一个String,那么每一个元素都是char.他的长度为2bytes 也就是2*8=16bits
    public static final int LENGTH = Character.SIZE;

    // msk是二进制1000，0000，0000，0000
    public static final int MSK = 0x8000;

    // 我们可以从元素层面，来创建一个该位置为1的mask
    // 举例说明：如果bitIndex所在元素为1011，0100，0101，1010 且 bitIndex在该元素的第五位，那么我们将获得0000，1000，0000，0000
    private int mask(int indexInBitsForACertainElement) {
        return MSK >>> indexInBitsForACertainElement;
    }


    @Override
    public int lengthInBits(String key) {
        return key == null ? 0 : key.length() * LENGTH;
    }

    @Override
    public int bitsPerElement() {
        return LENGTH; // 在String中为16
    }

    @Override
    public boolean isBitSet(String key, int bitIndex, int lengthInBits) {
        if(key == null){
            throw new NullPointerException("the key must not be null！");
        }

        if (bitIndex >= lengthInBits) {
            throw new IndexOutOfBoundsException("bitIndex is not less than the length in bits");
        }
        // 获取bitIndex 所在元素的 index
        int index = bitIndex / bitsPerElement();
        int indexInBitsForACertainElement = bitIndex % bitsPerElement();
        int mask = mask(indexInBitsForACertainElement);
        char element = key.charAt(index);
        return (element & mask) != 0;
    }

    @Override
    public boolean isPrefix(String prefix, int offsetInBits, int lengthBits, String key) {
        return false;
    }


}
