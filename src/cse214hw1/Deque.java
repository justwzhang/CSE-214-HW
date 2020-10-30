package cse214hw1;

public interface Deque<T> {
    /**
     * Inserts the specified element at the front of this deque.
     * @param t the element to add
     */
    void addFirst(T t); // [5 points]
    /**
     * Inserts the specified element at the end of this deque.
     * @param t the element to add
     */
    void addLast(T t); // [5 points]

    /**
     * Retrieves and removes the first element of this deque, throwing an exception if this deque is empty.
     * @return the first element of this deque
     * @throws java.util.NoSuchElementException if this deque is empty
     */
    T removeFirst(); // [5 points]
    /**
     * Retrieves and removes the last element of this deque, throwing an exception if this deque is empty.
     * @return the first element of this deque
     * @throws java.util.NoSuchElementException if this deque is empty
     */
    T removeLast(); // [5 points]
}
