package com.xusong.collectionmark1.iterator;

import com.xusong.collectionmark1.Unmodifiable;

import java.util.ListIterator;

/**
 * Created by xusong on 2018/7/21.
 */
public final class UnmodifiableListIterator<E> implements ListIterator<E>, Unmodifiable {

    private final ListIterator<? extends E> listIterator;


    /*这是一个迭代器的装换程序，我们可以把正在使用的列表迭代器转化为不可更改的集合迭代器*/
    public static <E> ListIterator<E> unmodifiableListIterator(final ListIterator<? extends E> iterator) {
        if (iterator == null) {
            throw new NullPointerException("ListIterator must not be null");
        }
        if (iterator instanceof Unmodifiable) {
            return (ListIterator<E>) iterator;
        }
        return new UnmodifiableListIterator<E>(iterator);

    }

    private UnmodifiableListIterator(ListIterator<? extends E> listIterator) {
        super();
        this.listIterator = listIterator;
    }


    @Override
    public boolean hasNext() {
        return listIterator.hasNext();
    }

    @Override
    public E next() {
        return listIterator.next();
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }

    @Override
    public E previous() {
        return listIterator.previous();
    }

    @Override
    public int nextIndex() {
        return listIterator.nextIndex();
    }

    @Override
    public int previousIndex() {
        return listIterator.previousIndex();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove() is not supported");
    }

    @Override
    public void set(E obj) {
        throw new UnsupportedOperationException("remove() is not supported");
    }

    @Override
    public void add(E obj) {
        throw new UnsupportedOperationException("remove() is not supported");
    }
}
