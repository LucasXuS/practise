package com.xusong.collectionmark1.list;

import com.xusong.collectionmark1.OrderedIterator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by xusong on 2018/7/22.
 */
public abstract class AbstractLinkedList<E> implements List<E> {


    // 链表头，不保存任何参数，链表为环状结构，因此这既是链表头，也是链表尾。
    // 这是一个设计算法时使用的参数，使用者并不知道这个节点存在。
    transient Node<E> header;

    // 记录链表被操作次数
    transient int modCount;

    // 链表长度，header不再长度范围内
    transient int size;

    //---------------------------------------初始化和构造器部---------------------------------
    protected AbstractLinkedList() {
        super();
    }

    // 三步走，运行超类代码，创建header节点，将初始化的collection加入
    protected AbstractLinkedList(final Collection<? extends E> coll) {
        super();
        init();
        addAll(coll);
    }

    // 创建header
    protected void init() {
        header = createHeaderNode();
    }

    //---------------------------------------------------------------------------------------

    //---------------------------------------节点操作部---------------------------------------

    // 创建一个自连接的节点，即便存在这个节点，使用者也认为这是一个空链表。
    protected Node<E> createHeaderNode() {
        return new Node<>();
    }

    // 创建一个不连接任何其他节点的节点，只赋值。链接问题交给addNode等解决。
    // 为节点赋值不增加修改次数。
    protected Node<E> createNode(final E value) {
        return new Node<>(value);
    }


    // 为某个节点赋值。注意，修改节点内容不增加修改次数。
    protected void updateNode(final Node<E> node, final E value) {
        node.setValue(value);
    }

    // 链表添加节点。添加在某个已知节点的前面。因此，已知节点是否存在需要提前验证，在此处不再验证。
    protected void addNode(final Node<E> nodeToInsert, final Node<E> insertBeforeNode) {
        nodeToInsert.next = insertBeforeNode;
        nodeToInsert.previous = insertBeforeNode.previous;
        insertBeforeNode.previous.next = nodeToInsert;
        insertBeforeNode.previous = nodeToInsert;
        modCount++;
        size++;
    }

    // 链表删除节点。因此，已知节点是否存在需要提前验证，在此处不再验证。
    protected void removeNode(final Node<E> nodeToRemove) {
        nodeToRemove.next.previous = nodeToRemove.previous;
        nodeToRemove.previous.next = nodeToRemove.next;
        modCount++;
        size--;
    }

    // 删除所有节点，只剩下header，只剩header时，链表尺寸是0。
    protected void removeAllNodes() {
        header.next = header;
        header.previous = header;
        size = 0;
        modCount++;
    }

    // 和addNode()功能内容相同。只不过addNode()中被添加的是node而不是value，在此函数中需要先创建节点。
    // 与此同时，已知节点是否存在需要提前验证，在此处不再验证。
    protected void addNodeBefore(final Node<E> node, final E value) {
        Node<E> nodeToInsert = createNode(value);
        addNode(nodeToInsert, node);
    }

    // 和addNode()功能有两处不同:
    // 第一，要加在已知node之后，等同加在已知node.next之前
    // 第二，addNode()中被添加的是node而不是value，在此函数中需要先创建节点。
    // 与此同时，已知节点是否存在需要提前验证，在此处不再验证。
    protected void addNodeAfter(final Node<E> node, final E value) {
        Node<E> nodeToInsert = createNode(value);
        addNode(nodeToInsert, node.next);
    }


    // 因为链表是双向链表，这样我们应该先进行二分，再进行查找 ，这样可以提升搜索效率。
    // 实际上可以用递归更大限度提升搜索效率。apache公司并未进行这样的设计，当size很大时，搜索速度只有o(n)
    // 参数endMarkerAllowed，当其为true时可能返回header否则不会返回。作为protected函数，这是一个
    // 用于算法内部的getNode函数，而用户只能使用get(index)
    protected Node<E> getNode(final int index, final boolean endMarkerAllowed) {

        final String exceptionPrefix = "Couldn't get the Node:index (";
        if (index < 0) {
            throw new IndexOutOfBoundsException(exceptionPrefix +
                    index + ") less than zero.");
        }

        if (!endMarkerAllowed && index == size) {
            throw new IndexOutOfBoundsException(exceptionPrefix +
                    index + ") is the size of the list.");
        }

        if (index > size) {
            throw new IndexOutOfBoundsException(exceptionPrefix +
                    index + ") is greater than the size of the" +
                    " list (" + size + ").");
        }

        Node<E> node;
        if (index < size / 2) {
            node = header.next;
            for (int currentIndex = 0; currentIndex < index; currentIndex++) {
                node = node.next;
            }
        } else {
            node = header;
            for (int currentIndex = size; currentIndex > index; currentIndex--) {
                node = node.previous;
            }
        }
        return node;
    }
    //------------------------------------------------------------------------------------------

    //------------------------------------链表底层添加删除查询部-----------------------------------

    // header是顶又是底，我们规定用户眼中的链表底部在header前一个。
    protected boolean addLast(final E o) {
        addNodeBefore(header, o);
        return true;
    }

    // header是顶又是底，我们规定用户眼中的链表底部在header后一个。
    protected boolean addFirst(final E o) {
        addNodeAfter(header, o);
        return true;
    }

    // 如果链表只有header本身，认为是空链表。
    protected E getFirst() {
        Node<E> node = header.next;
        if (node == header) {
            throw new NoSuchElementException();
        }
        return node.getValue();
    }

    // 如果链表只有header本身，认为是空链表。同时，我们这样的设计可以保证当只有一个元素时getLast()和getFirst()是同一个元素。
    protected E getLast() {
        Node<E> node = header.previous;
        if (node == header) {
            throw new NoSuchElementException();
        }
        return node.getValue();
    }

    // 先确认是否是空链表，再进行删除操作。同时将旧数值返回。
    protected E removeFirst() {
        Node<E> node = header.next;
        if (node == header) {
            throw new NoSuchElementException();
        }
        final E oldValue = node.getValue();
        removeNode(node);
        return oldValue;
    }

    // 先确认是否是空链表，再进行删除操作。同时将旧数值返回。
    protected E removeLast() {
        Node<E> node = header.previous;
        if (node == header) {
            throw new NoSuchElementException();
        }
        final E oldValue = node.getValue();
        removeNode(node);
        return oldValue;
    }
    //--------------------------------------------------------------------------------------------

    //--------------------------------------链表添加部(override)----------------------------------


    /**
     * 这些函数都来自于对底层接口的复写。
     */

    // 链表最底部添加一个元素。实际上我们的设计只会让它返回true。
    @Override
    public boolean add(final E o) {
        return addLast(o);
    }


    // 在目标节点前面添加一个元素。我们在getNode中已经做过了验证，如果元素不存在会抛出indexOutOfBoundsException。
    @Override
    public void add(final int index, final E o) {
        final Node<E> node = getNode(index, false);
        addNodeBefore(node, o);
    }

    // 将整个集合添加进链表的底部。该函数只会返回true。
    @Override
    public boolean addAll(final Collection<? extends E> collection) {
        return addAll(size, collection);
    }

    // 将整个集合添加进目标索引的后面。我们在getNode中已经做过了验证，如果元素不存在会抛出indexOutOfBoundsException。
    // 该函数只会返回true。
    @Override
    public boolean addAll(final int index, final Collection<? extends E> collection) {
        final Node<E> node = getNode(index, false);
        for (E o : collection) {
            addNodeBefore(node, o);
        }
        return true;
    }
    //---------------------------------------------------------------------------------------------

    //----------------------------------------------数组部-----------------------------------------
    @Override
    public Object[] toArray() {
        return toArray(new Object[size]);
    }


    // 如果，参数数组长度不够长，那么就拉长数组到足够容纳所有元素的程度
    // 如果数组过长，那么就在size 范围内将所有元素放入，但是在size+1的位置放置一个Null
    // 要考虑到返回值上溯造型的情况
    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            final Class<?> componentType = a.getClass().getComponentType();
            a = (T[]) Array.newInstance(componentType, size);
        }
        int i = 0;
        for (Node<E> node = header.next; node != header; node = node.next, i++) {
            a[i] = (T) node.getValue();
        }
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }
    //---------------------------------------------------------------------------------------------


    //------------------------------------------List基础函数部--------------------------------------

    // 长度不包括header
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    // 要求参数容器中的所有元素都要在链表中才会返回true
    @Override
    public boolean containsAll(Collection<?> c) {
        for (final Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public E get(int index) {
        Node<E> node = getNode(index, false);
        return node.getValue();
    }

    // 返回原值
    @Override
    public E set(int index, E element) {
        final Node<E> node = getNode(index, false);
        final E oldValue = node.getValue();
        updateNode(node, element);
        return oldValue;
    }

    // 正向遍历，返回第一个查到的索引，如果搜不到，就返回-1
    @Override
    public int indexOf(Object o) {
        int i = 0;
        for (Node<E> node = header.next; node != header; node = node.next) {
            if (isEqualValue(node.getValue(), o)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    // 逆向遍历，返回第一个查到的索引，如果搜不到，就返回-1
    @Override
    public int lastIndexOf(Object o) {
        int i = size - 1;
        for (Node<E> node = header.previous; node != header; node = node.previous) {
            if (isEqualValue(node.getValue(), o)) {
                return i;
            }
            i--;
        }
        return -1;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return new LinkedSubList<>(this, fromIndex, toIndex);
    }

    //---------------------------------------------------------------------------------------------

    //------------------------------------------Object基础函数部------------------------------------

    @Override
    public int hashCode() {
        int hashCode = 1;
        for (E e : this) {
            hashCode = 31 * hashCode + e.hashCode();
        }
        return hashCode;
    }


    // 如果是同一个对象返回true
    // 如果二者类型，或者同一个位置的元素不同，二者尺寸不同都返回false
    @Override
    public boolean equals(Object obj) {
        // 同一个对象，就直接返回true
        if (obj == this) {
            return true;
        }
        // 如果不是一种类型，直接返回false
        if (!(obj instanceof List)) {
            return false;
        }
        final List<?> other = (List<?>) obj;
        // 如果二者尺寸不相等，直接返回false
        if (other.size() != size()) {
            return false;
        }
        Iterator<?> iterator1 = listIterator();
        Iterator<?> iterator2 = other.listIterator();
        // 开始一个个的进行比较，如果有任何一个位置的元素不同，就返回false
        while (iterator1.hasNext() && iterator2.hasNext()) {
            final Object obj1 = iterator1.next();
            final Object obj2 = iterator2.next();
            if (!(obj1 == null ? obj2 == null : obj1.equals(obj2))) {
                return false;
            }
        }
        // 这实际上是第二次size比较
        return !(iterator1.hasNext() || iterator2.hasNext());
    }


    // 如果没有元素，直接返回 []
    // 如果有元素，中间用 ", "分隔开
    // 要考虑元素中有自身而产生无限递归而导致程序崩溃的情况
    @Override
    public String toString() {
        if (size() == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder(16 * size());
        sb.append("[");
        final Iterator<E> iterator = iterator();
        boolean hasNext = iterator.hasNext();
        while (hasNext) {
            final Object value = iterator.next();
            // 之所以这样写是因为 打印本身会自动调用toString()函数，这样很容易产生无限递归，导致和风煦崩溃
            sb.append(value == this ? "(this Collection)" : value);
            hasNext = iterator.hasNext();
            if (hasNext) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    //---------------------------------------------------------------------------------------------

    //------------------------------------链表删除部(override)--------------------------------------

    // 搜索链表，删除第一个符合的元素.找到该元素返回true，否则返回false
    @Override
    public boolean remove(final Object o) {
        for (Node<E> node = header.next; node != header; node = node.next) {
            if (isEqualValue(node.value, o)) {
                removeNode(node);
                return true;
            }
        }
        return false;
    }

    // 搜索链表，删除第一个存在于参数集合的元素。找到参数集合中任何一个元素返回true，否则返回false。
    // 不对参数验空指针。
    // 可以认为retainAll 和 removeAll某种程度上说是一对互为补集的操作
    @Override
    public boolean removeAll(final Collection<?> c) {
        boolean modified = false;
        final Iterator<E> iterator = iterator();
        while (iterator.hasNext()) {
            if (c.contains(iterator.next())) {
                iterator.remove();
                modified = true;
            }
        }

        return modified;
    }

    //  取参数容器的交集，将原列表删除至只有交集为止。如果有删除动作，就返回true，否则返回false
    @Override
    public boolean retainAll(final Collection<?> c) {
        boolean modified = false;
        final Iterator<E> iterator = iterator();
        while (iterator.hasNext()) {
            if (!c.contains(iterator.next())) {
                iterator.remove();
                modified = true;
            }
        }

        return modified;
    }

    @Override
    public E remove(final int index) {
        final Node<E> node = getNode(index, false);
        final E oldValue = node.getValue();
        removeNode(node);
        return oldValue;
    }

    @Override
    public void clear() {
        removeAllNodes();
    }


    //--------------------------------------------------------------------------------------------

    //--------------------------------------------迭代器部-----------------------------------------

    // 本质上三种类回复的迭代器都属于同一种类型,也就是我们创建的LinkedListIterator
    @Override
    public Iterator<E> iterator() {
        return listIterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        return new LinkedListIterator<>(this, 0);
    }

    @Override
    public ListIterator<E> listIterator(final int fromIndex) {
        return new LinkedListIterator<>(this, fromIndex);
    }

    //  下面的两个是为子列表创建迭代器。实际上我们在LinkedSubList中的迭代器函数也是用的这个函数
    public ListIterator<E> createSubListIterator(final LinkedSubList<E> subList) {
        return createSubListIterator(subList, 0);
    }

    public ListIterator<E> createSubListIterator(final LinkedSubList<E> subList, final int fromIndex) {
        return new LinkedSubListIterator<>(subList, fromIndex);
    }

    //--------------------------------------------------------------------------------------------


    //--------------------------------------------常用函数部---------------------------------------

    private boolean isEqualValue(Object obj1, Object obj2) {
        return (obj1 == obj2) || (obj1 != null && obj1.equals(obj2));
    }

    //---------------------------------------------------------------------------------------------

    //---------------------------------------序列化和反序列化部-------------------------------------

    //序列化,将对象放在参数流里
    protected void doWriteObject(final ObjectOutputStream outputStream) throws IOException {
        outputStream.writeInt(size());
        for (final E e : this) {
            outputStream.writeObject(e);
        }
    }

    //反序列化,将流里面的数据变成对象
    protected void doReadObject(final ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        init();
        final int size = inputStream.readInt();
        for (int i = 0; i < size; i++) {
            add((E) inputStream.readObject());
        }
    }

    //---------------------------------------------------------------------------------------------


    protected static class LinkedListIterator<E> implements ListIterator<E>, OrderedIterator<E> {

        // iterator所依托的链表。
        AbstractLinkedList<E> parent;

        // 下一个返回值
        Node<E> next;
        // 刚才返回过的数值
        Node<E> current;
        // 下一个返回值的索引
        int nextIndex;
        // 和modCount一起用，iterator在使用时不应该受到干扰。
        int expectedModCount;

        //--------------------------------------------常用函数部---------------------------------------

        public LinkedListIterator(final AbstractLinkedList<E> parent, final int fromIndex) {
            this.parent = parent;
            this.current = null;
            this.next = parent.getNode(fromIndex, true);
            this.nextIndex = fromIndex;
            expectedModCount = parent.modCount;
        }

        //---------------------------------------------------------------------------------------------

        //--------------------------------------------常用函数部---------------------------------------
        protected void checkModCount() {
            if (expectedModCount != parent.modCount) {
                throw new ConcurrentModificationException();
            }
        }

        //---------------------------------------------------------------------------------------------


        //--------------------------------------------常用迭代器函数部----------------------------------
        @Override
        public boolean hasNext() {
            return next != parent.header;
        }

        // 我们只在乎next是不是header,但是我们会放任next变成header
        @Override
        public E next() {
            checkModCount();
            if (!hasNext()) {
                throw new NoSuchElementException("No element at index " + nextIndex);
            }
            final E value = next.getValue();
            current = next;
            next = next.next;
            nextIndex++;
            return value;
        }

        @Override
        public boolean hasPrevious() {
            return next.previous != parent.header;
        }

        // 使用这个可以导致current 和 next相等 这个设定对下一次使用next有好处
        @Override
        public E previous() {
            checkModCount();
            if (!hasPrevious()) {
                throw new NoSuchElementException("No element at index " + nextIndex);
            }
            next = next.previous;
            final E value = next.getValue();
            nextIndex--;
            // 在这个时刻current 设定和next一样
            current = next;
            return value;
        }

        @Override
        public int nextIndex() {
            return nextIndex;
        }

        // 这个写法是一个纯展示 nextIndex没有发生任何改变
        @Override
        public int previousIndex() {
            return nextIndex() - 1;
        }
        //---------------------------------------------------------------------------------------------

        //--------------------------------------------增删查改部---------------------------------------
        @Override
        public void remove() {
            checkModCount();
            // 这是考虑了使用了previous()的情形
            if (current == next) {
                next = next.next;
                parent.removeNode(getLastNodeReturned());
            } else {
                parent.removeNode(getLastNodeReturned());
                nextIndex--;
            }
            parent.removeNode(getLastNodeReturned());
            current = null;
            expectedModCount++;
        }

        @Override
        public void set(final E e) {
            checkModCount();
            getLastNodeReturned().setValue(e);
        }

        // 即返回当前指针，如果当前指针是Null(因为我们初始化迭代器会将current设置为null),那么抛出非法状态的异常
        protected Node<E> getLastNodeReturned() {
            if (current == null) {
                throw new IllegalStateException();
            }
            return current;
        }

        // 当前指向元素改为e 也就是在当前Next之前放置元素
        // 由于当前指向添加的元素，相当于当前的指向向后走了一个，那么索引会后移。
        // 更新expectedModCount ，我们允许这样的操作。
        @Override
        public void add(final E e) {
            checkModCount();
            parent.addNodeBefore(next, e);
            current = null;
            nextIndex++;
            expectedModCount++;
        }
        //---------------------------------------------------------------------------------------------
    }


    // 在这个类中，我们并不会真的创建一个新的List,出于对内存的节约工作，我们会通过操作原有的List来展现出subList的效果
    protected static class LinkedSubList<E> extends AbstractList<E> {
        // 链表本体
        AbstractLinkedList<E> parent;
        // 这是一个相对稳定的变量，这个用于做偏移量
        int offset;
        // 这个也是一个辅助变量，用于展现subList的尺寸
        int size;
        // 和modCount一起用，保证在迭代的时候尺寸不会变
        int expectedModCount;

        //---------------------------------------初始化和构造器部----------------------------------
        public LinkedSubList(AbstractLinkedList<E> parent, int fromIndex, int toIndex) {
            if (toIndex > parent.size()) {
                throw new IndexOutOfBoundsException("toIndex = " + toIndex);
            }
            if (fromIndex < 0) {
                throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
            }
            if (fromIndex > toIndex) {
                throw new IllegalArgumentException("fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
            }
            this.parent = parent;
            this.offset = fromIndex;
            this.size = toIndex - fromIndex;
            this.expectedModCount = parent.modCount;
        }
        //-----------------------------------------------------------------------------------------

        //---------------------------------------操作查验部-----------------------------------------
        protected void rangeCheck(final int index, final int beyond) {
            if (index < 0 || index >= beyond) {
                throw new IndexOutOfBoundsException("Index (" + index + ") out of bounds for size (" + beyond + ").");
            }
        }

        protected void checkModCount() {
            if (expectedModCount != parent.modCount) {
                throw new ConcurrentModificationException();
            }
        }
        //-------------------------------------------------------------------------------------------

        //---------------------------------------添加部-----------------------------------------------

        @Override
        public void add(int index, E element) {
            rangeCheck(index, size);
            checkModCount();
            parent.add(element);
            expectedModCount++;
            size++;
            LinkedSubList.this.modCount++;
        }

        @Override
        public boolean addAll(Collection<? extends E> c) {
            return addAll(size, c);
        }

        @Override
        public boolean addAll(int index, Collection<? extends E> c) {
            rangeCheck(index, size);
            checkModCount();
            int cSize = c.size();
            if (cSize == 0) {
                return false;
            }
            parent.addAll(index + offset, c);
            size += cSize;
            expectedModCount = parent.modCount;
            // 底层的modCount也要变，个人觉得是协变的时候iterator中和expectedModCount配合。
            LinkedSubList.this.modCount++;
            return true;
        }
        //--------------------------------------------------------------------------------------------

        //---------------------------------------删除部------------------------------------------------
        @Override
        public E remove(int index) {
            rangeCheck(index, size);
            checkModCount();
            final E e = parent.remove(index + offset);
            expectedModCount = parent.modCount;
            LinkedSubList.this.modCount++;
            size--;
            return e;
        }

        @Override
        public void clear() {
            checkModCount();
            Iterator<E> iterator = iterator();
            while (iterator.hasNext()) {
                // 从这段代码可以看出，迭代器最关心的还是Next，最长使用的也是next，这也是迭代器存在的意义。
                iterator.next();
                iterator.remove();
            }
        }

        //---------------------------------------------------------------------------------------------

        //------------------------------------------List基础函数部--------------------------------------
        @Override
        public E set(final int index, final E element) {
            rangeCheck(index, size);
            checkModCount();
            return parent.set(index + offset, element);
        }

        @Override
        public E get(int index) {
            rangeCheck(index, size);
            checkModCount();
            return parent.get(index);
        }

        @Override
        public int size() {
            checkModCount();
            return size;
        }

        @Override
        public List<E> subList(int fromIndex, int toIndex) {
            return new LinkedSubList<>(parent, fromIndex, toIndex);
        }
        //--------------------------------------------------------------------------------------------


        //--------------------------------------------迭代器部-----------------------------------------
        @Override
        public Iterator<E> iterator() {
            return parent.createSubListIterator(this);
        }

        @Override
        public ListIterator<E> listIterator(int index) {
            return parent.createSubListIterator(this, index);
        }

        //---------------------------------------------------------------------------------------------

    }

    // 对LinkedListIterator进行修正
    protected static class LinkedSubListIterator<E> extends LinkedListIterator<E> {

        protected final LinkedSubList<E> sub;

        public LinkedSubListIterator(LinkedSubList<E> sub, int fromIndex) {
            super(sub.parent, fromIndex);
            this.sub = sub;
        }

        @Override
        public boolean hasNext() {
            return nextIndex() < sub.size;
        }

        @Override
        public boolean hasPrevious() {
            return previousIndex() >= 0;
        }

        @Override
        public int nextIndex() {
            return super.nextIndex() - sub.offset;
        }

        @Override
        public void remove() {
            super.remove();
            sub.expectedModCount = parent.modCount;
            sub.size--;
        }

        @Override
        public void add(final E e) {
            super.add(e);
            sub.expectedModCount = parent.modCount;
            sub.size++;
        }
    }


    protected static class Node<E> {

        public Node<E> getPreviousNode() {
            return previous;
        }

        public void setPreviousNode(final Node<E> previous) {
            this.previous = previous;
        }

        protected Node<E> previous;

        public Node<E> getNextNode() {
            return next;
        }

        public void setNextNode(final Node<E> next) {
            this.next = next;
        }

        protected Node<E> next;

        public E getValue() {
            return value;
        }

        public void setValue(final E value) {
            this.value = value;
        }

        protected E value;

        Node() {
            this.previous = this;
            this.next = this;
        }

        Node(final E value) {
            this.value = value;
        }

        Node(final Node<E> previous, final Node<E> next, final E value) {
            this.previous = previous;
            this.next = next;
            this.value = value;
        }

    }

}
