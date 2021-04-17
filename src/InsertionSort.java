
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * This class implements the Sorter interface and uses the Insertion Sort
 * algorithm as the chosen implementation.
 *
 * @author Justin Zhang
 * @param <E> a generic type
 */
public class InsertionSort<E extends Comparable<E>> implements Sorter<E> {

    private List<E> elements;
    private Order   order;
    private List<E> originalElements;
    private StringBuilder builder;
    private String  alg;
    private boolean isSorted;

    public InsertionSort(List<E> elements, Order order) {
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
     * This sorts the backing list of elements. The algorithm used is Insertion Sort
     * which takes into consideration on whether the list should be sorted
     * in increasing order or in decreasing order. It also uses the string builder
     * to create to return string for the show method.
     *
     * @throws NullPointerException if the elements list is null
     */
    @Override
    public void sort() {
        if(elements == null) throw new NullPointerException();
        if(order == Order.INCREASING){
            for(int i=1;i<=elements.size(); i++){
                int j = i-1;
                if(i<elements.size())
                    builder.append(elements.get(i));
                while(j>0 && elements.get(j).compareTo(elements.get(j-1))<0 ){
                    exchange(j,j-1);
                    j--;
                }
                if(i != elements.size())
                    builder.append(" :: " + elements + "\n");
                else
                    builder.append(elements);
            }
        }else{
            for(int i=1;i<=elements.size(); i++){
                int j = i-1;
                if(i<elements.size())
                    builder.append(elements.get(i));
                while(j>0 && elements.get(j).compareTo(elements.get(j-1))>0 ){
                    exchange(j,j-1);
                    j--;
                }
                if(i != elements.size())
                    builder.append(" :: " + elements + "\n");
                else
                    builder.append(elements);
            }
        }

        if(!isSorted)
            alg = builder.toString();
        isSorted = true;
    }

    /**
     * This is a helper method to exchange two elements within the list
     * elements at the two given indices.
     *
     * @param i the first input index to be swapped with the other.
     * @param j the second input index to be swapped with the other.
     */
    public void exchange(int i, int j){
        E temp = elements.get(i);
        elements.set(i, elements.get(j));
        elements.set(j, temp);
    }
    /**
     * The method displays the original input (unsorted) list, and then, each step is shown in a new line. For example,
     * if the original list to be sorted in increasing order is [6, 5, 3, 1, 8, 7, 2, 4], calling this method should
     * return the following string (exactly in this format):
     * <p>
     * 5 :: [6, 5, 3, 1, 8, 7, 2, 4]
     * 3 :: [5, 6, 3, 1, 8, 7, 2, 4]
     * 1 :: [3, 5, 6, 1, 8, 7, 2, 4]
     * 8 :: [1, 3, 5, 6, 8, 7, 2, 4]
     * 7 :: [1, 3, 5, 6, 8, 7, 2, 4]
     * 2 :: [1, 3, 5, 6, 7, 8, 2, 4]
     * 4 :: [1, 2, 3, 5, 6, 7, 8, 4]
     * [1, 2, 3, 4, 5, 6, 7, 8]
     * <p>
     * At each step, the element being inspected for insertion is at the start, and the list in its current state is
     * then placed after ::, two colon symbols. Notice the repeated list when the element being inspected for insertion
     * is 8, and the list does not change at all. Such repetitions must be included in the returned string.
     *
     *
     * @throws NullPointerException if the elements list is null.
     * @return the string representation of the step-wise transformation of the input list, as done with insertion sort
     */
    @Override
    public String show() {
        if(elements == null) throw new NullPointerException();
        return alg;
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
     * Just an example showing how your code will be used. The same list of elements used to explain the show() method
     * is being used in this example. Notice that this main method actually doesn't care about the type of algorithm
     * used by the sorter. For example, you could have a BubbleSort, MergeSort, or QuickSort implemenation instead, and
     * someone using your code would not need to change anything!
     *
     * This is a type of "abstraction" in code. We are ending this semester on this note, because you will learn a lot
     * more about abstraction in CSE 216.
     */
    public static void main(String... args) {
        InsertionSort<Integer> intsorter = new InsertionSort<>(Arrays.asList(15, 20, 10, 18), Order.INCREASING);
        intsorter.sort();
        System.out.println(intsorter.show());
        System.out.println(intsorter.getList());
        System.out.println();
//        intsorter.sort();
//        System.out.println(intsorter.show());
//        System.out.println("Decreasing");
//        InsertionSort<Integer> intsorter2 = new InsertionSort<>(Arrays.asList(6, 5, 3, 1, 8, 7, 2, 4), Order.DECREASING);
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
//        InsertionSort<Integer> list1 = new InsertionSort<>(llist, Order.INCREASING);
//        InsertionSort<Integer> list2 = new InsertionSort<>(llist2, Order.INCREASING);
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
