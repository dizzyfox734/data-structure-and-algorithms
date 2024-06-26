package ArrayQueue;

import java.util.NoSuchElementException;

import Interface.Queue;

public class ArrayQueue<E> implements Queue<E> {

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    private static final int DEFAULT_CAPACITY = 64;

    private Object[] array;
    private int size;
    private int front;
    private int rear;

    public ArrayQueue() {
        this.array = new Object[DEFAULT_CAPACITY];
        this.size = 0;
        this.front = 0;
        this.rear = 0;
    }

    public ArrayQueue(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException();
        }

        this.array = new Object[capacity];
        this.size = 0;
        this.front = 0;
        this.rear = 0;
    }

    private int rangeCheck(int oldCapacity, int newCapacity) {
        if (MAX_ARRAY_SIZE - size <= 0) {
            throw new OutOfMemoryError("Queue length is too large");
        }

        if (newCapacity >= 0) {
            if (newCapacity - MAX_ARRAY_SIZE <= 0) {
                return newCapacity;
            }
            return MAX_ARRAY_SIZE;
        } else {
            int fiveFourthsize = oldCapacity + (oldCapacity >>> 2);
            if (fiveFourthsize <= 0 || fiveFourthsize >= MAX_ARRAY_SIZE) {
                return MAX_ARRAY_SIZE;
            }
            return fiveFourthsize;
        }
    }

    private void resize(int newCapacity) {
        int arrayCapacity = array.length;
        newCapacity = rangeCheck(arrayCapacity, newCapacity);
        Object[] newArray = new Object[newCapacity];

        for (int i = 1, j = front + 1; i <= size; i++, j++) {
            newArray[i] = array[j % arrayCapacity];
        }

        this.array = null;
        this.array = newArray;
        this.front = 0;
        this.rear = this.size;
    }

    @Override
    public boolean offer(E item) {
        if ((rear + 1) % array.length == front) {
            resize(array.length * 2);
        }

        rear = (rear + 1) % array.length;

        array[rear] = item;
        size++;

        return true;
    }

    @Override
    public E poll() {
        if (size == 0) {
            return null;
        }

        front = (front + 1) % array.length;

        @SuppressWarnings("unchecked")
        E item = (E) array[front];

        array[front] = null;
        size--;

        if (array.length > DEFAULT_CAPACITY && size < (array.length / 4)) {
            resize(Math.max(DEFAULT_CAPACITY, array.length / 2));
        }

        return item;
    }

    public E remove() {
        E item = poll();

        if (item == null) {
            throw new NoSuchElementException();
        }

        return item;
    }

    @Override
    public E peek() {
        if (size == 0) {
            return null;
        }

        @SuppressWarnings("unchecked")
        E item = (E)array[(front + 1) % array.length];
        return item;
    }

    public E element() {
        E item = peek();

        if (item == null) {
            throw new NoSuchElementException();
        }

        return item;
    }
}
