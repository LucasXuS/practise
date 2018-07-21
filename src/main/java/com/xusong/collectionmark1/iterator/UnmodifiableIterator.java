package com.xusong.collectionmark1.iterator;

import com.xusong.collectionmark1.Unmodifiable;

import java.util.Iterator;

/**
 * Created by xusong on 2018/7/21.
 */
public final class UnmodifiableIterator<E> implements Iterator<E>, Unmodifiable {

    private final Iterator<? extends E> iterator;

    public static <E> Iterator<E> unmodifiableIterator(Iterator<? extends E> iterator) {
        if (iterator == null) {
            throw new NullPointerException("iterator must not be null");
        }

        if (iterator instanceof Unmodifiable) {
            return (Iterator<E>) iterator;
        }
        return new UnmodifiableIterator<E>(iterator);
    }


    private UnmodifiableIterator(Iterator<? extends E> iterator) {
        super();
        this.iterator = iterator;
    }


    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove() is not supported");
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public E next() {
        return iterator.next();
    }
}
