package cse214hw1;

public class MainMethodsForHW1 {
    /**
     * Tests the methods for Homework Q1.
     */
    public static void main(String[] args){
        int[] p = new int[] {1,2,3,4,5};
        ArrayUtils.rotate(p, 11);
        for(int i=0; i<p.length;i++){
            System.out.print(p[i] + " ");
        }
        System.out.println();
        char[] q = new char[] {'a','b','c','d','e'};
        ArrayUtils.rotate(q, 12);
        for(int i=0; i<q.length;i++){
            System.out.print(q[i] + " ");
        }
        System.out.println();
        int[] c = new int[] {1,2,3,4,5};
        int[] d = new int[] {6,7,8,9,10};
        int[] e = ArrayUtils.merge(c, d);
        for(int i=0; i<e.length;i++){
            System.out.print(e[i] + " ");
        }
        /**
         * The main method to test all methods for Homework Q2.
         * @param args
         */
        System.out.println();
        System.out.println();
        ArrayDeque<Integer> deque = new ArrayDeque<Integer>();
        deque.addFirst(1);
        deque.print();
        deque.addFirst(2);
        deque.print();
        deque.addLast(3);
        deque.print();
        deque.removeFirst();
        deque.print();
        deque.removeLast();
        deque.print();
        ArrayDeque<Double> doubles = ArrayDeque.of(2.0, 4.0, 5.25);
        System.out.println();
        Deque<String> a = new ArrayDeque<>();
        Deque<String> b = new ArrayDeque<>(100);
        /**
         * This test all methods in Homework Q3
         */
        ArrayQueue<Integer> q1 = new ArrayQueue<>();
        q1.add(1);
        q1.add(2);
        q1.print();
        q1.add(3);
        q1.print();
        q1.add(4);
        q1.print();
        q1.remove();
        q1.print();
        System.out.println(q1.peek());
        q1.remove();
        q1.print();
        q1.add(5);
        q1.print();

    }
}
