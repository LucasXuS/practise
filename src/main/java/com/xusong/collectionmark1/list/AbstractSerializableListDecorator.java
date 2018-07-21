package com.xusong.collectionmark1.list;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.List;

/**
 * Created by xusong on 2018/7/21.
 */
public abstract class AbstractSerializableListDecorator<E>
        extends AbstractListDecorator<E> {

    protected AbstractSerializableListDecorator(final List<E> list) {
        super(list);
    }


    private void writeObject(final ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(decorated());
    }


    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        setCollection((Collection<E>) in.readObject());
    }


}
