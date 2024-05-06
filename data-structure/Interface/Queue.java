package Interface;

public interface Queue<E> {

    /**
     * Add an element at the end of the queue.
     * 
     * @param e element to add to the queue
     * @return return true if elements are successfully added to the queue
     */
    boolean offer(E e);

    /**
     * Remove the first element of the queue and return the removed element.
     * 
     * @return return the removed element
     */
    E poll();

    /**
     * Return the first element of the queue.
     */
    E peek();

    /**
     * Remove the first element in the queue and return the removed element.
     * If the queue is empty, make an exception.
     * 
     * @return return the first element of the queue
     * @throws NoSuchElementException queue is empty
     */
    E remove();

    /**
     * Return the first element of the queue, but dose not remove it.
     * if the queue is empty, make an exception
     * 
     * @return return the first element of the queue
     * @throws NoSuchElementException queue is empty
     */
    E element();
}