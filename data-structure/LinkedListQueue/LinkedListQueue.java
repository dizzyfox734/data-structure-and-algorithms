package LinkedListQueue;

import java.util.NoSuchElementException;

import Interface.Queue;

public class LinkedListQueue<E> implements Queue<E> {
    
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public LinkedListQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public boolean offer(E value) {
        Node<E> newNode = new Node<E>(value);

        if (size == 0) {
            head = newNode;
        } else {
            tail.next = newNode;
        }

        tail = newNode;
        size++;

        return true;
    }

    @Override
    public E poll() {
        if (size == 0) {
            return null;
        }

        E element = head.data;

        Node<E> nextNode = head.next;

        head.data = null;
        head.next = null;

        head = nextNode;
        size--;

        return element;
    }

    public E remove() {
        E element = poll();

        if (element == null) {
            throw new NoSuchElementException();
        }

        return element;
    }

    @Override
    public E peek() {
        if (size == 0) {
            return null;
        }

        return head.data;
    }

    public E element() {
        E element = peek();

        if (element == null) {
            throw new NoSuchElementException();
        }

        return element;
    }
}
