package collection;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

/**
 * 装饰器模式，装饰Collection类，去提供一系列的新的行为。
 * <p>
 * 每一个函数都是在装饰这个Collection类，这是一个框架去扩展一些同步的和不可改变的行为。在这里使用装饰类的好处
 * 是，装饰器可以运用在所有这个类的派生类中。但是，这有个缺点，每次创建派生类，我们要复写所有的函数。
 * <p>
 * 我们这个类对iterator 不做任何处理。为了保证能够优化集合类本身，我们决定从被包装的Collection类中返回数值，
 * 但是，这样有可能不太会如我们所愿。比如，如果你试图去写一个不可变的implementation。极有可能会产生漏洞。
 * <p>
 * 这个函数对Object类两个重要的函数 hashCode() 和 equals() 没有任何修改，它将依赖于其他类后续的继承和implementation.
 *
 *
 *
 */
public abstract class AbstractCollectionDecorator<E>
        implements Collection<E>, Serializable {

    /**
     * 需要被装饰的集合
     */
    private Collection<E> collection;


    /**
     * 这个构造器只是为了逆序列化，在实际中不使用。
     */
    protected AbstractCollectionDecorator() {
        super();
    }

    /**
     * 构造器
     *
     * @param coll 需要被装饰的集合。不可以被空。
     * @throws NullPointerException 如果参数集合为空就会被抛出。
     */
    protected AbstractCollectionDecorator(final Collection<E> coll) {
        if (coll == null) {
            throw new NullPointerException("Collection must not be null.");
        }
    }


    /**
     * 获取被装饰的集合
     * <p>
     * 我们所有获取被装饰的集合都在这一个函数中
     */
    protected Collection<E> decorated() {
        return collection;
    }

    /**
     * 设置集合。
     *
     * @param coll 被装饰的集合。
     */
    protected void setCollection(final Collection<E> coll) {
        this.collection = coll;
    }

    //--------------------------------------------------------------------


    @Override
    public boolean add(final E object) {
        return decorated().add(object);
    }

    @Override
    public boolean addAll(final Collection<? extends E> coll) {
        return decorated().addAll(coll);
    }


    @Override
    public void clear() {
        decorated().clear();
    }

    @Override
    public boolean contains(final Object object) {
        return decorated().contains(object);
    }

    @Override
    public boolean isEmpty() {
        return decorated().isEmpty();
    }

    @Override
    public Iterator<E> iterator() {
        return decorated().iterator();
    }

    @Override
    public boolean remove(final Object object) {
        return decorated().remove(object);
    }

    @Override
    public int size() {
        return decorated().size();
    }

    @Override
    public Object[] toArray() {
        return decorated().toArray();
    }

    @Override
    public <T> T[] toArray(final T[] object) {
        return decorated().toArray(object);
    }

    @Override
    public boolean containsAll(final Collection<?> coll) {
        return decorated().containsAll(coll);
    }

    @Override
    public boolean removeAll(final Collection<?> coll) {
        return decorated().removeAll(coll);
    }

    @Override
    public boolean retainAll(final Collection<?> coll) {
        return decorated().retainAll(coll);
    }

    @Override
    public String toString() {
        return decorated().toString();
    }
}
