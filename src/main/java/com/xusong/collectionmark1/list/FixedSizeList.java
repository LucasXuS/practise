package com.xusong.collectionmark1.list;

import com.xusong.collectionmark1.BoundedCollection;

import java.util.List;

/**
 * 固定长度列表类
 *
 * 我们要让所有改变尺寸的操作直接抛异常
 */
public class FixedSizeList<E>
        extends AbstractSerializableListDecorator<E>
        implements BoundedCollection<E>{
    protected FixedSizeList(final List<E> list) {
        super(list);
    }

    @Override
    public boolean isFull() {
        return true;
    }

    @Override
    public int maxSize() {
        return size();
    }


    /*
    * 需要改变的方法列表
    * add * 2
    * addAll * 2
    * clear
    *
    * lastIndexOf
    *
    * remove * 2
    * removeAll
    * retainAll
    *
    *
    * **/



}
