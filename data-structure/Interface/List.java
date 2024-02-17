package Interface;

/**
 * be userd to ArrayList, SinglyLinkedList, DoublyLinkedList
 * 
 * @param <E> the type of elements in this List
 */
public interface List<E> {

     /*
     * Add an element to List
     * 
     * @param value element to add to List.
     * @return When duplicaes are not allowed in List,
     *          if there are already duplicate elements in List return {@code false},
     *          and if there are no duplicate elements return {@code true}
     */
     boolean add(E value);

     /**
     * Add an element to List at a specific position
     * 
     * @param index specific position variable to add an element to List
     * @param value element to add to List
     */
     void add(int index, E value);

     /**
     * Remove the element at the index position of List
     * 
     * @param index position variable to remove from List
     * @return removed element
     */
     E remove(int index);

     /**
     * Remove a specific element from List
     * If there are multiple identical elements, only the first element found is removed
     * 
     * @param value element to remove from List
     * @return If there is no element to remove in List or removal fails return {@code false}
     *          if removal is successful return {@code true}
     */
     boolean remove(Object value);

     /**
     * Return the element of specific position of List
     * 
     * @param index position variable
     * @return element at the index position of List
     */
     E get(int index);

     /**
     * Replace an element at a specific position in List with a new element
     * 
     * @param index position variable
     * @param value new element to replace
     */
     void set(int index, E value);

     /**
     * Check whether a specific element is in List
     * 
     * @param value specific element to find in List
     * @return If a specific element exists in List, return {@code true},
     *          if it does not exist, return {@code false}
     */
     boolean contains(Object value);

     /**
     * Return the position of a specific element in List
     * 
     * @param value element to find position in List
     * @return the position that matches the first element in List
     *          if there is no matching element, return -1
     */
     int indexOf(Object value);

     /**
     * Return the number of elements in List
          * 
          * @return the number of elements in List  
          */
     int size();

     /**
     * Return whether List is empty
     * 
          * @return If there is no element in List, return {@code true},
     *          if there is an element or elements in List, return {@code false} 
          */
     boolean isEmpty();

     /**
     * Remove all elements in List
          */
     public void clear();
}
