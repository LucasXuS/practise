package list;

import collection.AbstractCollectionDecorator;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

public abstract class AbstractListDecorator<E> extends AbstractCollectionDecorator<E>
        implements List<E> {

    protected AbstractListDecorator() {
        super();
    }

    protected AbstractListDecorator(final List<E> list) {
        super(list);
    }

    @Override
    protected List<E> decorated() {
        return (List<E>) super.decorated();
    }

    @Override
    public boolean equals(final Object object) {
        return object == this || decorated().equals(object);
    }

    @Override
    public int hashCode() {
        return decorated().hashCode();
    }

    //-----------------------------------------------------------------------------


    @Override
    public void add(final int index, final E object) {
        decorated().add(index, object);
    }

    @Override
    public boolean addAll(final int index, final Collection<? extends E> coll) {
        return decorated().addAll(index, coll);
    }

    @Override
    public E get(final int index) {
        return decorated().get(index);
    }

    @Override
    public E set(final int index, final E object) {
        return decorated().set(index, object);
    }


    @Override
    public ListIterator<E> listIterator() {
        return decorated().listIterator();
    }

    @Override
    public ListIterator<E> listIterator(final int index) {
        return decorated().listIterator(index);
    }


    @Override
    public E remove(final int index) {
        return decorated().remove(index);
    }


}
