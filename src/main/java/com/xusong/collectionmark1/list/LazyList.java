package list;


import java.util.List;
import Fac

/**
 * 简单来说这个类也是使用装饰器模式
 *
 * 当我们使用 {@link #get(int)} 方法大于范围(bounds)的时候，
 * 代码会自动增加list 的尺寸，并且在index给出的位置从工厂方法中创建一个对象（这个工厂是类的使用者给出的）。
 * 中间都塞null进去
 */
public class LazyList<E>
        extends AbstractSerializableListDecorator<E> {
    protected LazyList(List<E> list) {
        super(list);
    }


    protected LazyList(final List<E> list, Factory<E>)
}
