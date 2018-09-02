package com.xusong.collectionmark1;

import java.util.SortedMap;

public interface Trie<K, V> extends IterableSortedMap<K, V>{


    /**
     * 通过prefix寻找元素：
     * 如，树里面有 'Anna','Anael','Analu','Andrea','Andres','Andrea' and 'Anatole' 如果我们搜索 'And'
     * 那么我们会得到 'Andreas', 'Andrea','Andres'
     * */
    SortedMap<K, V> prefixMap(K key);
}
