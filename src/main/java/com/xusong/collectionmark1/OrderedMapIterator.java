package com.xusong.collectionmark1;


// 似乎ordered 都意味着前序遍历，OrderedIterator就是如此
public interface OrderedMapIterator<K, V> extends MapIterator<K, V>, OrderedIterator<K> {

    @Override
    boolean hasPrevious();

    @Override
    K previous();
}
