package com.xusong.collectionmark1.iterator;

import java.util.ListIterator;

/**
 * Created by xusong on 2018/7/21.
 */
public class AbstractListIteratorDecorator<E> implements ListIterator<E> {

    private final ListIterator<E> iterator;


    public AbstractListIteratorDecorator(final ListIterator<E> iterator) {
        if (iterator == null) {
            throw new NullPointerException("Iterator must not be null");
        }
        this.iterator = iterator;
    }

    protected ListIterator<E> getListIterator(){
        return iterator;
    }


    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public E next() {
        return iterator.next();
    }

    @Override
    public boolean hasPrevious() {
        return iterator.hasPrevious();
    }

    @Override
    public E previous() {
        return iterator.previous();
    }

    @Override
    public int nextIndex() {
        return iterator.nextIndex();
    }

    @Override
    public int previousIndex() {
        return iterator.previousIndex();
    }

    @Override
    public void remove() {
        iterator.remove();
    }

    @Override
    public void set(E obj) {
        iterator.set(obj);
    }

    @Override
    public void add(E obj) {
        iterator.add(obj);
    }
}
