package com.xusong.collectionmark1.trie;

import com.xusong.collectionmark1.Trie;

import java.io.Serializable;
import java.util.AbstractMap;

public abstract class AbstractBitwiseTrie<K, V> extends AbstractMap<K, V>
        implements Trie<K, V>, Serializable {
}
