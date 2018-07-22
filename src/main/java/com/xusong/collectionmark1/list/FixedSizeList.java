package com.xusong.collectionmark1.list;

import com.xusong.collectionmark1.BoundedCollection;
import com.xusong.collectionmark1.iterator.AbstractListIteratorDecorator;
import com.xusong.collectionmark1.iterator.UnmodifiableIterator;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * 固定长度列表类
 * <p>
 * 我们要让所有改变尺寸的操作直接抛异常
 */
public class FixedSizeList<E>
        extends AbstractSerializableListDecorator<E>
        implements BoundedCollection<E> {

    protected FixedSizeList(final List<E> list) {
        super(list);
    }

    @Override
    public boolean add(E object) {
        throw new UnsupportedOperationException("List is fixed size");
    }

    @Override
    public void add(int index, E object) {
        throw new UnsupportedOperationException("List is fixed size");
    }

    @Override
    public boolean addAll(Collection<? extends E> coll) {
        throw new UnsupportedOperationException("List is fixed size");
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> coll) {
        throw new UnsupportedOperationException("List is fixed size");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("List is fixed size");
    }

    @Override
    public E get(int index) {
        return decorated().get(index);
    }

    @Override
    public int indexOf(Object object) {
        return decorated().indexOf(object);
    }

    @Override
    public Iterator<E> iterator() {
        return UnmodifiableIterator.unmodifiableIterator(decorated().iterator());
    }

    @Override
    public int lastIndexOf(Object object) {
        return decorated().lastIndexOf(object);
    }

    @Override
    public ListIterator<E> listIterator() {
        return new FixedSizeListIterator(decorated().listIterator(0));
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new FixedSizeListIterator(decorated().listIterator(index));
    }

    @Override
    public E remove(int index) {
        return decorated().remove(index);
    }

    @Override
    public boolean remove(Object object) {
        throw new UnsupportedOperationException("List is fixed size");
    }

    @Override
    public boolean removeAll(Collection<?> coll) {
        throw new UnsupportedOperationException("List is fixed size");
    }

    @Override
    public boolean retainAll(Collection<?> coll) {
        throw new UnsupportedOperationException("List is fixed size");
    }

    @Override
    public E set(int index, E object) {
        return decorated().set(index, object);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        final List<E> sub = decorated().subList(fromIndex, toIndex);
        return new FixedSizeList<E>(sub);
    }

    /*
    * 这个迭代器只允许通过set()来改变
    *
    * **/
    private class FixedSizeListIterator extends AbstractListIteratorDecorator<E> {

        protected FixedSizeListIterator(ListIterator<E> iterator) {
            super(iterator);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("List is fixed size");
        }

        @Override
        public void add(Object object) {
            throw new UnsupportedOperationException("List is fixed size");
        }

    }

    @Override
    public boolean isFull() {
        return true;
    }

    @Override
    public int maxSize() {
        return size();
    }


}
