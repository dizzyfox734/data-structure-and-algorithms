package LinkedListDeque;

import java.util.NoSuchElementException;

import Interface.Queue;

public class LinkedListDeque<E> implements Queue<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public LinkedListDeque() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean offerFirst(E value) {
        Node<E> newNode = new Node<E>(value);
        newNode.next = head;

        if (head != null) {
            head.prev = newNode;
        }

        head = newNode;
        size++;

        if (head.next == null) {
            tail = head;
        }
        return true;
    }

    @Override
    public boolean offer(E value) {
        return offerLast(value);
    }

    public boolean offerLast(E value) {
        if (size == 0) {
            return offerFirst(value);
        }

        Node<E> newNode = new Node<E>(value);

        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
        size++;

        return true;
    }

    @Override
    public E poll() {
        return pollFirst();
    }

    public E pollFirst() {
        if (size == 0) {
            return null;
        }

        E element = head.data;

        Node<E> nextNode = head.next;

        head.data = null;
        head.next = null;

        if (nextNode != null) {
            nextNode.prev = null;
        }
        head = null;
        head = nextNode;
        size--;

        if (size == 0) {
            tail = null;
        }

        return element;
    }

    public E remove() {
        return removeFirst();
    }

    public E removeFirst() {
        E element = poll();

        if (element == null) {
            throw new NoSuchElementException();
        }
        return element;
    }

    public E pollLast() {
        if (size == 0) {
            return null;
        }

        E element = tail.data;

        Node<E> prevNode = tail.prev;

        tail.data = null;
        tail.prev = null;

        if (prevNode != null) {
            prevNode.next = null;
        }

        tail = null;
        tail = prevNode;
        size--;

        if (size == 0) {
            head = null;
        }
        return element;
    }

    public E removeLast() {
        E element = pollLast();

        if (element == null) {
            throw new NoSuchElementException();
        }
        return element;
    }
    @Override
    public E peek() {
        return peekFirst();
    }

    public E peekFirst() {
        if(size == 0) {
            return null;
        }

        return head.data;
    }

    public E peekLast() {
        if(size == 0) {
            return null;
        }

        return tail.data;
    }

    public E element() {
        return getFirst();
    }
        
    public E getFirst() {
        E item = peek();

        if(item == null) {
            throw new NoSuchElementException();
        }
        return item;	
    }

    public E getLast() {
        E item = peekLast();

        if(item == null) {
            throw new NoSuchElementException();
        }
        return item;
    }
}
