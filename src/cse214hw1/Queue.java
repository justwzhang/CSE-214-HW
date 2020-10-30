package cse214hw1;

public interface Queue<T> {
    /**
     * Inserts the specified element into this queue
     * @param t the element to add
     */
    void add(T t); // [3 points]
    /**
     * Retrieves and removes the head of this queue.
     * @return the first item of this queue
     * @throws java.util.NoSuchElementException if this queue is empty
     */
    T remove(); // [3 points]
    /**
     * Retrieves, but does not remove, the head of this queue.
     * @return the first item of this queue
     * @throws java.util.NoSuchElementException if this queue is empty
     */
    T peek(); // [3 points]
}
