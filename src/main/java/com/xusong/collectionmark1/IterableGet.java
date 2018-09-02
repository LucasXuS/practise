package com.xusong.collectionmark1;

public interface IterableGet<K, V> extends Get<K, V> {



    /**
     * apache 给了我们一个使用示例
     * <pre>
     *     IteratableMap<String, Integer> map = new HashMap<String, Integer>();
     *     MapIterator it = map.mapIterator();
     *     while(it.hasNext()){
     *         String key = it.next();
     *         Integer value = it.getValue();
     *         it.setValue(value + 1);
     *     }
     *
     * </pre>
     * */
    MapIterator<K, V> mapIterator();
}
