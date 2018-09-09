package com.xusong.collectionmark1.trie;

abstract class AbstractPatriciaTrie<K, V> extends AbstractBitwiseTrie<K, V> {
    protected AbstractPatriciaTrie(KeyAnalyzer<? super K> keyAnalyzer) {
        super(keyAnalyzer);
    }
}
