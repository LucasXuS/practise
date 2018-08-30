package com.xusong.collectionmark1;

import java.util.Iterator;


// 这个接口将对向前遍历进行一些定义。
public interface OrderedIterator<E> extends Iterator<E> {

    boolean hasPrevious();

    E previous();
}
