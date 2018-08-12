package com.xusong.collectionmark1.list;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by xusong on 2018/7/22.
 */
public abstract class AbstractLinkedList<E> implements List<E> {


    // 链表头，不保存任何参数，链表为环状结构，因此这既是链表头，也是链表尾。
    // 这是一个设计算法时使用的参数，使用者并不知道这个节点存在。
    transient Node<E> header;

    // 记录链表被操作次数
    transient int modCount;

    // 链表长度
    transient int size;

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
        if (index < 0) {
            throw new IndexOutOfBoundsException("Couldn't get the Node:" +
                    "index (" + index + ") less than zero.");
        }

        if (!endMarkerAllowed && index == size) {
            throw new IndexOutOfBoundsException("Couldn't get the Node:" +
                    "index (" + index + ") is the size of the list.");
        }

        if (index > size) {
            throw new IndexOutOfBoundsException("Couldn't get the Node:" +
                    "index (" + index + ") is greater than the size of the" +
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
    //------------------------------------------------------------------------------------------

    //--------------------------------------链表添加部(override)----------------------------------


    /**
     * 这些函数都来自于对底层接口的复写。
     * */

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
    //--------------------------------------------------------------------------------------------

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
    @Override
    public boolean removeAll(final Collection<?> c) {


        return false;
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        return false;
    }

    @Override
    public E remove(final int index) {
        return null;
    }


    //--------------------------------------------------------------------------------------------

    //--------------------------------------------迭代器部-----------------------------------------

    //--------------------------------------------------------------------------------------------

    //-------------------------------常用函数-------------------------------------

    private boolean isEqualValue(Object obj1, Object obj2) {
        return (obj1 == obj2) && (obj1 != null && obj1.equals(obj2));
    }

    //---------------------------------------------------------------------------


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
