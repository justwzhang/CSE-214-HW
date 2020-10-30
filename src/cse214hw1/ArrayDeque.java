//Justin Zhang 112615200
package cse214hw1;

public class ArrayDeque<T> implements Deque {

    private T[] deque = (T[])new Object[2];
    private int size;

    /**
     * The two constructors for this class, one default and one that determines the initial caacity
     */
    public ArrayDeque() {
    }
    public ArrayDeque(int capacity) {
        T[] temp = (T[])new Object[capacity];
    }

    /**
     * Inserts and element at the beginning of the deque.
     * @param o the element to add
     */
    @Override
    public void addFirst(Object o) {
        size++;
        if(size>=deque.length)
            resize();
        T prev = deque[0];
        for(int i=1; i<size; i++){
            T temp = deque[i%deque.length];
            deque[i%deque.length] = prev;
            prev = temp;
        }
        deque[0] = (T)o;
    }
    /**
     * Inserts an element at the end of the deque.
     * @param o the element to add
     */
    @Override
    public void addLast(Object o) {
        size++;
        if(size>=deque.length)
            resize();
        int index = 0;
        while(deque[index%deque.length] != null){
            index++;
        }
        deque[index%deque.length] = (T)o;
    }

    /**
     * Removes the first element of the deque.
     * @return the first element of the deque
     * @throws java.util.NoSuchElementException if this deque is empty.
     */
    @Override
    public Object removeFirst() {
        if(size == 0)
            throw new java.util.NoSuchElementException();
        T first = deque[0];
        for(int i=0; i<size-1; i++){
            deque[i] = deque[(i+1)%deque.length];
        }
        deque[size-1] = null;
        size--;
        return first;
    }

    /**
     * Removes the last element of the deque.
     * @return the last element of the deque.
     *  @throws java.util.NoSuchElementException if this deque is empty.
     */
    @Override
    public Object removeLast() {
        if(size == 0)
            throw new java.util.NoSuchElementException();
        T last = deque[size-1];
        deque[size-1] = null;
        size--;
        return last;
    }

    /**
     * Resizes the array to twice the size of the current number of elements.
     */
    private void resize(){
        T[] temp = (T[])new Object[size * 2];
        for(int i=0; i<size; i++){
            temp[i] = deque[i];
        }
        deque = temp;
    }

    /**
     * Prints out the deque in a neat and clean format.
     */
     protected void print(){
        System.out.print("[");
        for(int i =0; i<size; i++){
            if(i != size-1)
                System.out.print(deque[i] + ", ");
            else
                System.out.print(deque[i]);
        }
        System.out.println("]");
    }

    /**
     * A static method that creates an ArrayDeque class of type T.
     * @param args is the array that will be iterated into the Deque.
     * @return The deque that is created from this method.
     */
    public static <T> ArrayDeque<T> of(T... args){
        ArrayDeque<T> temp = new ArrayDeque<>();
        for(int i=0; i<args.length; i++){
            temp.addLast(args[i]);
        }
        return temp;
    }
}
