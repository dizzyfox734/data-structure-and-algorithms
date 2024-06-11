package HashSet;

import Interface.Set;

public class HashSet<E> implements Set<E> {

    private final static int DEFAULT_CAPACITY = 1 << 4;
    private final static float LOAD_FACTOR = 0.75f;

    Node<E>[] table;
    private int size;

    @SuppressWarnings("unchecked")
    public HashSet() {
        table = (Node<E>[]) new Node[DEFAULT_CAPACITY];
        size = 0;
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

        int oldCapacity = table.length;
        int newCapacity = oldCapacity << 1;

        final Node<E>[] newTable = (Node<E>[]) new Node[newCapacity];

        for (int i = 0; i < oldCapacity; i++) {
            Node<E> data = table[i];

            if (data == null) {
                continue;
            }

            table[i] = null;

            if (data.next == null) {
                newTable[data.hash & (newCapacity - 1)] = data;
                continue;
            }

            Node<E> lowHead = null;
            Node<E> lowTail = null;
            Node<E> highHead = null;
            Node<E> highTail = null;

            Node<E> next;

            do {
                next = data.next;

                if ((data.hash & oldCapacity) == 0) {
                    if (lowHead == null) {
                        lowHead = data;
                    } else {
                        lowTail.next = data;
                    }
                    lowTail = data;
                } else {
                    if (highHead == null) {
                        highHead = data;
                    } else {
                        highTail.next = data;
                    }
                    highTail = data;
                }

                data = next;
            } while (data != null);

            if (lowTail != null) {
                lowTail.next = null;
                newTable[i] = lowHead;
            }

            if (highTail != null) {
                highTail.next = null;
                newTable[i + oldCapacity] = highHead;
            }
        }
        table = newTable;
    }

    public boolean add(E e) {
        return add(hash(e), e) == null;
    }

    private E add(int hash, E key) {
        int idx = hash % table.length;

        if (table[idx] == null) {
            table[idx] = new Node<E>(hash, key, null);
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

            prev.next = new Node<E>(hash, key, null);
        }
        size++;

        if (size >= LOAD_FACTOR * table.length) {
            resize();
        }
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return remove(hash(o), o) != null;
    }

    private Object remove(int hash, Object key) {
        int idx = hash % table.length;

        Node<E> node = table[idx];
        Node<E> removeNode = null;
        Node<E> prev = null;

        if (node == null) {
            return null;
        }

        while (node != null) {
            if (node.hash == hash && (node.key == key || node.key.equals(key))) {
                removeNode = node;

                if (prev == null) {
                    table[idx] = node.next;
                    node = null;
                } else {
                    prev.next = node.next;
                    node = null;
                }
                size--;
                break;
            }
            prev = node;
            node = node.next;
        }

        return removeNode;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        int idx = hash(o) % table.length;
        Node<E> temp = table[idx];

        while (temp != null) {
            if (o == temp.key || (o != null && o.equals(temp.key))) {
                return true;
            }
            temp = temp.next;
        }

        return false;
    }

    @Override
    public void clear() {
        if (table != null && size > 0) {
            for (int i = 0; i < table.length; i++) {
                table[i] = null;
            }
            size = 0;
        }
    }
}
