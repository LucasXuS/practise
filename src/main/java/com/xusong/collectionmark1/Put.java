package com.xusong.collectionmark1;

import java.util.Map;

public interface Put<K, V> {

    void clear();

    Object put(K key, V value);

    void putAll(Map<? extends K, ? extends V> t);
}
