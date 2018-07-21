package com.xusong.collectionmark1.list;


import com.xusong.collectionmark1.Factory;

import java.util.List;

/**
 * 简单来说这个类也是使用装饰器模式
 * <p>
 * 当我们使用 {@link #get(int)} 方法大于范围(bounds)的时候，
 * 代码会自动增加list 的尺寸，并且在index给出的位置从工厂方法中创建一个对象（这个工厂是类的使用者给出的）。
 * 中间都塞null进去
 */
public class LazyList<E>
        extends AbstractSerializableListDecorator<E> {
    protected LazyList(List<E> list) {
        super(list);
    }

    private Factory<? extends E> factory;

    protected LazyList(final List<E> list, final Factory<? extends E> factory) {
        super(list);
        if (factory == null) {
            throw new IllegalArgumentException("Factory must not be null");
        }
        this.factory = factory;
    }

    public static <E> LazyList<E> lazyList(final List<E> list, final Factory<? extends E> factory) {
        return new LazyList<E>(list, factory);
    }

    @Override
    public E get(int index) {
        int size = size();
        if (index < size) {

            /*
            *
            * 如果这个位置本身就是有值，那么我们就返回这个值，和别的一样
            * */
            E object = decorated().get(index);
            if (object == null) {

                /*
                *  因为根据我们的算法这个值有个能是占位符，是null 所以当它为空的时候要放个
                *  factory产生的对象进去
                * **/
                object = factory.create();
                decorated().set(index, object);
                return object;
            }
            return object;
        }
        /*
        * 塞占位符进去
        *
        * **/
        for (int i = 0; i < index; i++) {
            decorated().add(null);
        }

        /*
        * 如果占位符添加完成，就可以在相应的位置处添加本尊了。
        *
        * **/
        E object = factory.create();
        decorated().add(object);
        return object;
    }
}
