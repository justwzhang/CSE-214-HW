//justin zhang 112615200
package hw3.datastructures;

import java.util.LinkedList;

/**
 * This class implements the {@link Set} interface. It offers constant time performance (on average) for the basic
 * operations <code>add</code>, <code>remove</code>, <code>containt</code>, and <code>size</code>, under the simple
 * uniform hashing assumption (i.e., the hash function distributes elements uniformly across the slots in the backing
 * table).
 * There are two constructors given to you. You can modify them, or add new constructors. However, the signature of
 * these two constructors must not be changed. That is, the user must be able to create an instance of this class by
 * invoking <code>new ChainedHashSet()</code> and <code>new ChainedHashSet(int k)</code>.
 *
 * @param <E> the type of elements stored in this set
 */
public class ChainedHashSet<E> implements Set<E> {

    /**
     * Once an instance is created, this table size cannot change
     */
    private final int tablesize;
    private LinkedList<E>[] backingArr;

    // DO NOT MODIFY THIS METHOD
    public final int tablesize() { return this.tablesize; }

    // DO NOT MODIFY THIS METHOD
    public final double loadfactor() { return size() / (double) tablesize; }

    public ChainedHashSet()              { this(10); }

    public ChainedHashSet(int tablesize) {
        this.tablesize = tablesize;
        this.backingArr = new LinkedList[this.tablesize];
    }

    /**
     * @return the cardinality in this set
     */
    @Override
    public int size() {
        int count = 0;
        for(int i=0; i<backingArr.length; i++){
            if(backingArr[i] != null)
                for(int j=0; j<backingArr[i].size(); j++){
                    count++;
                }
        }
        return count;
    }

    /**
     * @return <code>true</code> if this set contains no elements, and <code>false</code> otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns <code>true</code> if this set contains the specified element, and <code>false</code> otherwise.
     *
     * @param element the element that we are checking if it is in the set
     * @return <code>true</code> if this set contains the element
     * @throws NullPointerException if the element is null
     */
    @Override
    public boolean contains(E element) {
        if(element == null){
            throw new NullPointerException();
        }
        int backingArrIndex = Math.abs(element.hashCode()) % tablesize;
        if(backingArr[backingArrIndex] != null) {
            for(int i=0; i<backingArr[backingArrIndex].size(); i++){
                if(element.equals(backingArr[backingArrIndex].get(i))){
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    /**
     * Adds the specified element to this set if it is not already present. If this set already contains the element,
     * the call leaves the set unchanged and returns <code>false</code>. There are no duplicate elements in this set.
     *
     * @param e the element to be added to this set
     * @return <code>true</code> if this set did not already contain {@literal e}, and <code>false</code>
     * otherwise
     * @throws NullPointerException if {@literal e} is null
     */
    @Override
    public boolean add(E e) {
        if(e == null)
            throw new NullPointerException();
        if(contains(e))return false;
        int backingArrIndex = Math.abs(e.hashCode()) % tablesize;
        if(backingArr[backingArrIndex] == null)
            backingArr[backingArrIndex] = new LinkedList<>();
        backingArr[backingArrIndex].add(0, e);
        return true;
    }

    /**
     * Removes the specified element from this set if it is present, and returns {@code true} if this set contained the
     * element and is removed from the set.
     *
     * @param e the element to be removed from this set
     * @return <code>true</code> if this set contained the specified element which was removed, and <code>false</code> otherwise
     * @throws NullPointerException if the specified element is null
     */
    @Override
    public boolean remove(E e) {
        if(e == null){
            throw new NullPointerException();
        }
        int backingArrIndex = Math.abs(e.hashCode()) % tablesize;
        if(backingArr[backingArrIndex] != null && backingArr[backingArrIndex].contains(e)){
            for(int i=0; i<backingArr[backingArrIndex].size(); i++){
                if(backingArr[backingArrIndex].size() == 1 && e.equals(backingArr[backingArrIndex].get(i))){
                    backingArr[backingArrIndex] = null;
                    break;
                }
                if(e.equals(backingArr[backingArrIndex].get(i))){
                    backingArr[backingArrIndex].remove(i);
                    break;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * This method returns a string showing the entire hash table structure of this set. The format must be as follows:
     * Suppose a table has four slots, with three elements 'a', 'b', 'c', hashed to the first slot and 'z' hashed to the
     * third slot. Printing out the returned string should show the following:
     *
     * 1 || a -> b -> c
     * 2 ||
     * 3 || z
     *
     * Note that the elements 'a', 'b', 'c', and 'z' must also be human-readable.
     *
     * @return a string representation of the entire set, showing the underlying hash table structure
     */
    @Override
    public String toString() {
        String returnString = "";
        for(int i=0; i<backingArr.length; i++){
            returnString += i+1;
            returnString += " || ";
            if(backingArr[i] != null)
                for(int j=0; j<backingArr[i].size(); j++) {
                    returnString += backingArr[i].get(j).toString();
                    if(j+1 != backingArr[i].size())
                        returnString += " -> ";
                }
            returnString += "\n";
        }
        return returnString;
    }
}
