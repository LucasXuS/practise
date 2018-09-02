package com.xusong.collectionmark1;


import java.util.Collection;
import java.util.Map;
import java.util.Set;

// 在这里，我们把Map的 read 部分提取出来
public interface Get<K, V> {

    boolean containsKey(Object key);

    boolean containsValue(Object value);

    Set<Map.Entry<K, V>> entrySet();

    V get(Object key);

    V remove(Object key);

    boolean isEmpty();

    Set<K> keySet();

    int size();

    Collection<V> values();

}
