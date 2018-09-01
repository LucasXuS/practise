package com.xusong.collectionmark1.collection;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;


// 在这里我们使用装饰器模式.其中把共性的函数提前设置好。
public abstract class AbstractCollectionDecorator<E> implements Collection<E>, Serializable {

    Collection<E> collection;

    // 在真正使用的时候你会感觉到，这个初始化设置为protected很有用，因为我们无法从外部直接使用这个类了，我们只能依靠派生类的方式使用这个类。
    protected AbstractCollectionDecorator(final Collection<E> coll) throws NullPointerException {
        if (coll == null) {
            throw new NullPointerException("Collection must not be null");
        }
        collection = coll;
    }

    protected void setCollection(final Collection<E> coll) {
        if (coll == null) {
            throw new NullPointerException("Collection must not be null");
        }
        collection = coll;
    }


    protected Collection<E> decorated() {
        return collection;
    }


    @Override
    public int size() {
        return decorated().size();
    }

    @Override
    public boolean isEmpty() {
        return decorated().isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return decorated().contains(o);
    }


    @Override
    public boolean containsAll(Collection<?> c) {
        return decorated().containsAll(c);
    }

    @Override
    public Iterator<E> iterator() {
        return decorated().iterator();
    }

    @Override
    public Object[] toArray() {
        return decorated().toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return decorated().toArray(a);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return decorated().addAll(c);
    }

    @Override
    public boolean add(E e) {
        return decorated().add(e);
    }

    @Override
    public boolean remove(Object o) {
        return decorated().remove(o);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return decorated().removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return removeAll(c);
    }

    @Override
    public void clear() {
        decorated().clear();
    }

    @Override
    public String toString() {
        return decorated().toString();
    }
}
