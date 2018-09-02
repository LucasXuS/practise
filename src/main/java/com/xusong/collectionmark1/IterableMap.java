package com.xusong.collectionmark1;

import java.util.Map;

public interface IterableMap<K, V> extends Map<K, V>, IterableGet<K, V>, Put<K, V> {
}
