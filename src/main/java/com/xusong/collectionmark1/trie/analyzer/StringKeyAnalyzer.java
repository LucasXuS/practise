package com.xusong.collectionmark1.trie.analyzer;

import com.xusong.collectionmark1.trie.KeyAnalyzer;

public class StringKeyAnalyzer extends KeyAnalyzer<String> {
    @Override
    public int lengthInBits(String key) {
        return 0;
    }

    @Override
    public int bitsPerElement() {
        return 0;
    }

    @Override
    public boolean isBitSet(String key, int bitIndex, int lengthInBits) {
        return false;
    }


}
