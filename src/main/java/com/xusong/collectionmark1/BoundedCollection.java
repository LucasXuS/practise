package com.xusong.collectionmark1;

import java.util.Collection;

/**
 * 这种集合类尺寸都是可变的，但是尺寸却永远无法超越一个预设的尺寸最大值
 */
public interface BoundedCollection<E> extends Collection<E>{
    boolean isFull();

    int maxSize();

}
