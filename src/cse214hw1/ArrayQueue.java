//Justin Zhang 112615200
package cse214hw1;

public class ArrayQueue<T> implements Queue {
    private T[] queue = (T[])new Object[2];
    private int size = 0;
    private int firstElementIndex = 0;

    /**
     * Constructor with a predetermined capacity
     * @param capacity
     */
    public ArrayQueue(int capacity) {
        queue = (T[])new Object[capacity];
    }

    /**
     * A default constructor
     */
    public ArrayQueue() {
    }

    /**
     * Inserts an element into this queue.
     * @param o the element to add
     */
    @Override
    public void add(Object o) {
        if(size>=queue.length || (size + firstElementIndex)>=queue.length)
            resize();
        queue[(size + firstElementIndex)% queue.length] = (T)o;
        size++;

    }
    /**
     * Retrieves and removes the head/beginning of this queue.
     * @return the first element of this queue
     * @throws java.util.NoSuchElementException if this queue is empty
     */
    @Override
    public Object remove() {
        if(size == 0)
            throw new java.util.NoSuchElementException();
        T end = queue[firstElementIndex];
        queue[firstElementIndex] = null;
        firstElementIndex++;
        size--;
        return end;
    }
    /**
     * Retrieves, but does not remove, the head/beginning of this queue.
     * @return the first element of this queue
     * @throws java.util.NoSuchElementException if this queue is empty
     */
    @Override
    public Object peek() {
        if(size == 0)
            throw new java.util.NoSuchElementException();
        return queue[firstElementIndex];
    }

    /**
     * Changes the size of the array to double its original size
     */
    private void resize() {
        T[] temp = (T[]) new Object[(size + firstElementIndex) * 2];
        for (int i = 0; i < size+firstElementIndex; i++) {
            temp[i] = queue[i];
        }
        queue = temp;
    }

    /**
     * Prints the queue in a clean format
     */
    protected void print(){
        System.out.print("[");
        for(int i =firstElementIndex; i<size + firstElementIndex; i++){
            if(i != (size + firstElementIndex)-1)
                System.out.print(queue[i] + ", ");
            else
                System.out.print(queue[i]);
        }
        System.out.println("]");
    }
}
