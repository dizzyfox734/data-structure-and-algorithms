package LinkedHashSet;

import Interface.Set;

public class LinkedHashSet<E> implements Set<E> {

    private final static int DEFAULT_CAPACITY = 1 << 4;
    private final static float LOAD_FACTOR = 0.75f;

    Node<E>[] table;
    private int size;

    private Node<E> head;
    private Node<E> tail;

    @SuppressWarnings("unchecked")
    public LinkedHashSet() {
        table = (Node<E>[]) new Node[DEFAULT_CAPACITY];
        size = 0;
        head = null;
        tail = null;
    }

    private static final int hash(Object key) {
        int hash;
        if (key == null) {
            return 0;
        } else {
            return Math.abs(hash = key.hashCode()) ^ (hash >>> 16);
        }
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int newCapacity = table.length * 2;

        final Node<E>[] newTable = (Node<E>[]) new Node[newCapacity];

        for (int i = 0; i < table.length; i++) {
            Node<E> value = table[i];

            if (value == null) {
                continue;
            }

            table[i] = null;

            Node<E> nextNode;

            while (value != null) {
                int idx = value.hash % newCapacity;

                if (newTable[idx] != null) {
                    Node<E> tail = newTable[idx];

                    while (tail.next != null) {
                        tail = tail.next;
                    }
                    nextNode = value.next;
                    value.next = null;
                    tail.next = value;
                } else {
                    nextNode = value.next;
                    value.next = null;
                    newTable[idx] = value;
                }

                value = nextNode;
            }
        }
        table = null;
        table = newTable;
    }

    private void linkLastNode(Node<E> o) {

        Node<E> last = tail;

        tail = o;

        if (last == null) {
            head = o;
        } else {
            o.prevLink = last;
            last.nextLink = o;
        }
    }

    @Override
    public boolean add(E e) {
        return add(hash(e), e) == null;
    }
    
    private E add(int hash, E key) {
        int idx = hash % table.length;

        Node<E> newNode = new Node<E>(hash, key, null);

        if (table[idx] == null) {
            table[idx] = newNode;
        } else {
            Node<E> temp = table[idx];
            Node<E> prev = null;

            while (temp != null) {
                if ((temp.hash == hash) && (temp.key == key || temp.key.equals(key))) {
                    return key;
                }
                prev = temp;
                temp = temp.next;
            }

            prev.next = newNode;
        }
        size++;

        linkLastNode(newNode);

        if (size >= LOAD_FACTOR * table.length) {
            resize();
        }
        return null;
    }

    private void unlinkNode(Node<E> o) {
        Node<E> prevNode = o.prevLink;
        Node<E> nextNode = o.nextLink;

        if (prevNode == null) {
            head = nextNode;
        } else {
            prevNode.nextLink = nextNode;
            o.prevLink = null;
        }

        if (nextNode == null) {
            tail = prevNode;
        } else {
            nextNode.prevLink = prevNode;
            o.nextLink = null;
        }

    }

    @Override
    public boolean remove(Object o) {
        return remove(hash(o), o) != null;
    }

    private Object remove(int hash, Object key) {
        int idx = hash % table.length;

        Node<E> node = table[idx];
        Node<E> removedNode = null;
        Node<E> prev = null;

        if (node == null) {
            return null;
        }

        while (node != null) {
            if (node.hash == hash && (node.key == key || node.key.equals(key))) {
                removedNode = node;

                if (prev == null) {
                    table[idx] = node.next;
                } else {
                    prev.next = node.next;
                }

                unlinkNode(node);
                node = null;

                size--;
                break;
            }
            prev = node;
            node = node.next;
        }

        return removedNode;
    }
}
