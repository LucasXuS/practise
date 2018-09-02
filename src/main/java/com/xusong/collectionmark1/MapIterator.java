package com.xusong.collectionmark1;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * <pre>
 * MapIterator<String, Integer> it = map.mapIterator();
 * while (it.hasNext()) {
 *      String key = it.next();
 *      Integer value = it.getValue();
 *      it.setValue(value + 1);
 *
 * }
 * </pre>
 * <p>
 * 以上是apache给我们一个使用示例
 */
public interface MapIterator<K, V> extends Iterator<K> {

    boolean hasNext();

    /**
     * @return 迭代器中下一个key
     * @throws NoSuchElementException 如果迭代结束。
     **/
    K next();

    /**
     * 上次调用<code>next()</code>返回的元素的key
     *
     * @throws IllegalStateException 如果<code>next()</code>一次都没有被调用过
     **/
    K getKey();
    /**
     * 上次调用<code>next()</code>返回的元素的value
     *
     * @throws IllegalStateException 如果<code>next()</code>一次都没有被调用过
     **/
    V getValue();
    /**
     * 删除上次调用<code>next()</code>返回的节点
     *
     * @throws UnsupportedOperationException 如果这个map不支持删除操作
     * @throws IllegalStateException 如果<code>next()</code>一次都没有被调用过
     * @throws IllegalStateException 如果<code>remove()</code>被调用过后没有再次调用<code>next()</code>，再次调用<code>remove()</code>
     **/
    void remove();

    /**
     * 删除上次调用<code>next()</code>返回的节点
     *
     * @throws UnsupportedOperationException 如果这个map不支持设置操作
     * @throws IllegalStateException 如果<code>next()</code>一次都没有被调用过
     * @throws IllegalStateException 如果<code>remove()</code>被调用过后没有再次调用<code>next()</code>，而是直接调用<code>set()</code>
     **/
    void set(V value);


}
