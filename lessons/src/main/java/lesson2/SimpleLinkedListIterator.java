package lesson2;

import java.util.Iterator;

public class SimpleLinkedListIterator<E> implements Iterator<E> {

    private final SimpleLinkedListImpl<E> list;

    private LinkedList.Node<E> current;

    public SimpleLinkedListIterator(SimpleLinkedListImpl<E> list) {
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        if (current == null) {
            current = list.getFirstElement();
        } else {
            current = current.next;
        }
        return current != null;
    }

    @Override
    public E next() {
        return current.item;
    }
}
