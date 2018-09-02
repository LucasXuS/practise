package com.xusong.collectionmark1;

public interface OrderedMap<K, V> extends IterableMap<K, V> {

    OrderedMapIterator<K, V> mapIterator();

    K firstKey();

    K lastKey();

    K nextKey(K key);

    K previousKey(K key);
}
