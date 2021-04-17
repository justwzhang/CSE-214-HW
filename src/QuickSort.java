

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * This class implements the Sorter interface and uses the Quick Sort
 * algorithm as the chosen implementation.
 *
 * @author Justin Zhang
 * @param <E> the generic type
 */
public class QuickSort<E extends Comparable<E>> implements Sorter<E> {

    private List<E> elements;
    private Order   order;
    private List<E> originalElements;
    private StringBuilder  builder;
    private String  alg;
    private boolean isSorted;


    public QuickSort(List<E> elements, Order order) {
        this.elements = elements;
        this.order    = order;
        this.originalElements = duplicateList(elements);
        this.builder = new StringBuilder();
        this.alg = "";
        this.isSorted = false;
    }

    @Override
    public List<E> getList() {
        return elements;
    }

    /**
     * This method simply calls the other sort method and is what is accessed by instances of this
     * class.
     *
     * @throws NullPointerException if the elements list is null
     */
    @Override
    public void sort() {
        if(elements == null) throw new NullPointerException();
        if(builder.length() != 0) builder = new StringBuilder();
        sort(0, elements.size() - 1);
        if(!isSorted)
            alg = builder.toString();
        isSorted = true;
    }

    /**
     * This handles the recursive elements of the Quick Sort algorithm. It takes the starting and ending
     * indices of the sub-list that is to be sorted.
     *
     * @param start the starting index of the sub list to be sorted.
     * @param end the last index of the sub list to be sorted.
     */
    private void sort(int start, int end){
        int pivotInd;
        if(start < end){
            pivotInd = partitionIndex(start, end);
            sort(start, pivotInd);
            sort(pivotInd + 1, end);
        }
    }

    /**
     *
     * This method sorts sub-lists based on an increasing or decreasing order. This method
     * takes in the indices of the start and end index of the desired sub-array. It is a helper
     * method of the sort method and handles all of the swapping and partitioning. It also uses
     * a String builder to create a string for the show method.
     *
     * @param start the first index of the sub-list to be sorted.
     * @param end the last index of the sub-list to be sorted.
     * @return the index where the pivoted element resides
     */
    private int partitionIndex(int start, int end){
        if(order == Order.INCREASING){
            E pivotElement = elements.get(start);
            int i = start;
            int j = end+1;
            while(i<j){
                builder.append(pivotElement + " :: " + elements.toString()+ "\n");
                do{
                    i++;
                    if(i == end) break;
                }while(elements.get(i).compareTo(pivotElement)<0);

                do{
                    j--;
                    if(j == start) break;
                }while(elements.get(j).compareTo(pivotElement)>=0);
                if(i<j) {
                    exchange(i, j);
                }
            }
            exchange(start,j);
            builder.append("  :: " + elements.toString() + "\n");
            return j;
        }else{
            E pivotElement = elements.get(start);
            int i = start;
            int j = end+1;
            while(i<j){
                builder.append(pivotElement + " :: " + elements.toString()+ "\n");
                do{
                    i++;
                    if(i == end) break;
                }while(elements.get(i).compareTo(pivotElement)>0);

                do{
                    j--;
                    if(j == start) break;
                }while(elements.get(j).compareTo(pivotElement)<=0);
                if(i<j) {
                    exchange(i, j);
                }
            }

            exchange(start,j);
            builder.append("  :: " + elements.toString() + "\n");
            return j;
        }
    }
    /**
     * This is a helper method to exchange two elements within the list
     * elements at the two given indices.
     *
     * @param i the first input index to be swapped with the other.
     * @param j the second input index to be swapped with the other.
     */
    private void exchange(int i, int j){
        E temp = elements.get(i);
        elements.set(i, elements.get(j));
        elements.set(j, temp);
    }
    /**
     * Duplicates a given list into another list that does
     * not share the same memory location and thus will not
     * be modified when the original is modified.
     *
     * @param temp the input list to be duplicated.
     * @return a duplicated list of the input temp list.
     */
    private List<E> duplicateList(List<E> temp){
        List<E> newList = new LinkedList<>();
        if(temp == null) newList = null;
        for(int i=0; i<temp.size(); i++){
            newList.add(temp.get(i));
        }
        return newList;
    }
    /**
     * The method displays the original input (unsorted) list, and then, each step is shown in a new line. For example,
     * if the original list to be sorted in increasing order is [6, 4, 9, 5, 1, 8, 2, 7, 3], calling this method should
     * string as follows, exactly in the format shown:
     * <p>
     * 6 :: [6, 4, 9, 5, 1, 8, 2, 7, 3]
     * 6 :: [6, 4, 3, 5, 1, 8, 2, 7, 9]
     * 6 :: [6, 4, 3, 5, 1, 2, 8, 7, 9]
     *   :: [2, 4, 3, 5, 1, 6, 8, 7, 9]
     * <p>
     * Only the steps with the first pivot are shown above, and NOT the entire output. The last step with a specific
     * pivot does not show the pivot separately in front of the :: separator, to indicate that a different pivot will
     * be used in the next step. At each stage, you must use the first element as the pivot.
     *
     * This method is responsible for creating the StringBuilder that is used to create the desired String above.
     * @throws NullPointerException if the elements list is null
     * @return the string representation of the step-wise transformation of the input list, as done with quick sort
     */
    @Override
    public String show() {
        if(elements == null) throw new NullPointerException();
        return alg;
    }

    public static void main(String... args) {
//        QuickSort<Integer> intsorter = new QuickSort<>(Arrays.asList(6, 4, 5, 5, 5, 20, 2, 56, 3), Order.INCREASING);
//        intsorter.sort();
//        System.out.println(intsorter.show());
//        System.out.println(intsorter.getList());
//        System.out.println();
//////        intsorter.sort();
//////        System.out.println(intsorter.show());
//////        System.out.println("Decreasing");
//        QuickSort<Integer> intsorter2 = new QuickSort<>(Arrays.asList(6, 4, 5, 5, 3, 20, 2, 7, 1), Order.DECREASING);
//        intsorter2.sort();
//        System.out.println(intsorter2.show());
//        System.out.println(intsorter2.getList());

//        LinkedList<Integer> llist = new LinkedList<>();
//        llist.add(6);
//        llist.add(5);
//        llist.add(3);
//        llist.add(1);
//        llist.add(8);
//        llist.add(7);
//        llist.add(2);
//        llist.add(4);
//        ArrayList<Integer> llist2 = new ArrayList<>();
//        llist2.add(6);
//        llist2.add(5);
//        llist2.add(3);
//        llist2.add(1);
//        llist2.add(8);
//        llist2.add(7);
//        llist2.add(2);
//        llist2.add(4);
//        QuickSort<Integer> list1 = new QuickSort<>(llist, Order.INCREASING);
//        QuickSort<Integer> list2 = new QuickSort<>(llist2, Order.INCREASING);
//        list1.sort();
//        System.out.println(list1.show());
//        System.out.println(list1.getList());
//        list2.sort();
//        System.out.println(list2.show());
//        System.out.println(list2.getList());
        // NOTE: the list shown at the end of the string printed after calling show() MUST be identical to the result
        // calling getList() after calling sort(). That is, the backing list must actually be changed as is shown by the
        // result of the show() method.
    }
}
