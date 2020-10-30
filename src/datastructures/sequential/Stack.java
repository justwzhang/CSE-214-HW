//Justin Zhang 116215200
package datastructures.sequential;

import java.util.EmptyStackException;
    /**
     * This class implements the LIFOQueue which is a version of a stack ADT. This implementation uses a backing
     * singly linked list. This class is capable of using any type inputted into variable T
     *
    * @author Justin Zhang
    */
public class Stack<T> implements LIFOQueue{
    private SNode<T> head;

        /**
         * A general constructor that creates an object and leaves the head noad as null.
         *
         */
    public Stack(){
        head = null;
    }

    /**
     * Retrieves the head SNode
     *
     * @return The head of the stack
     */

    public SNode<T> getHead(){
        return head;
    }
    /**
     * Retrieves and removes the element at the top of this stack.
     * There will be an error if there are no elements in the stack.
     *
     * @return the element at the top of this stack.
     * @throws EmptyStackException if the stack is empty.
     */
    @Override
    public T pop() {
        if(head == null){
            throw new EmptyStackException();
        }
        T element = head.getData();
        head = head.getNext();
        return element;
    }
    /**
     * Pushes the specified element onto the top of this stack.
     *
     * @param element the element that us pushed onto the top of this stack.
     */
    @Override
    public void push(Object element) {
        if(head == null){
            head = new SNode<T>((T)element);

        }else {
            SNode<T> tempNode = new SNode<T>((T) element);
            tempNode.setNext(head);
            head = tempNode;
        }
    }
    /**
     * Retrieves, but does not remove, the element at the top of this stack.
     * There will be an error if there are no elements in the stack.
     *
     * @return the element at the top of this stack.
     * @throws EmptyStackException if the stack is empty.
     */
    @Override
    public T peek() {
        if(head == null){
            throw new EmptyStackException();
        }
        return head.getData();
    }
    /**
     * Returns the number of elements in this sequence.
     *
     * @return the number of elements in this sequence
     */
    @Override
    public int size() {
        if(head == null)
            return 0;
        int size = 0;
        Stack<T> tempStack = new Stack<T>();
        while(head.getNext() != null){
            tempStack.push((T)pop());
            size++;
        }
        tempStack.push((T)pop());
        size++;
        while(tempStack.getHead().getNext() != null){
            push(tempStack.pop());
        }
        push(tempStack.pop());
        return size;
    }
    /**
     * @return <code>true</code> if the sequence contains no elements, <code>false</code> otherwise.
     */
    @Override
    public boolean isEmpty() {
        if(head == null)
            return true;
        else
            return false;
    }
}
