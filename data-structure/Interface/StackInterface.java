package Interface;

public interface StackInterface<E> {

    /**
     * Add an element to the top of the stack.
     * 
     * @param item element to be added to the stack
     * @return element added to the stack
     */
    E push(E item);

    /**
     * Remove teh element at the top of the stack and return the removed element.
     * 
     * @return removed element
     */
    E pop();

    /**
     * Returns the element at the top of teh stack without removing it.
     * 
     * @return elements at the top of the stack
     */
    E peek();

    /**
     * Returns where a particular element is located from the top of the stack.
     * If there are overlapping elements, the location of the top elements if returned.
     * 
     * @param value the element to locate in the stasck
     * @return returns the location that matches the element for the first time from the top of the stack.
     *          returns -1 if there is no matching element
     */
    int search(Object value);

    /**
     * Returns the number of element in the stack.
     * 
     * @return returns the number of element in the stack
     */
    int size();

    /**
     * Delete all elements in the stack.
     */
    void clear();

    /**
     * Returns if an element is empty in the stack.
     * 
     * @return return {@code true} if there is no element in the stack, or {@code false} oterwise
     */
    boolean empty();
}
