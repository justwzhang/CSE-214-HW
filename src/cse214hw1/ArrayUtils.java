//Justin Zhang 112615200
package cse214hw1;

public class ArrayUtils {
    /**
     * Rotates the array given array by r number of elements to the left, i.e., for each index i, a[i]
     * moves to a[(i+r)].
     * @param a: the input array of of type int
     */
    public static void rotate(int[] a, int r){
        int counter = 0;
        int prev = a[0];
        while(counter != r) {
            for (int i = 0; i < a.length; i++) {
                if(i == 0){
                    a[i] = a[a.length-1];
                }else {
                    int temp = a[i % a.length];
                    a[i] = prev;
                    prev = temp;
                }
            }
            counter++;
        }
    }
    /**
     * Rotates the array given array by r number of elements to the left, i.e., for each index i, a[i]
     * moves to a[(i+r)].
     * @param a: the input array of type char
     */
    public static void rotate(char[] a, int r){
        int counter = 0;
        char prev = a[0];
        while(counter != r) {
            for (int i = 0; i < a.length; i++) {
                if(i == 0){
                    a[i] = a[a.length-1];
                }else {
                    char temp = a[i % a.length];
                    a[i] = prev;
                    prev = temp;
                }
            }
            counter++;
        }
    }
    /**
     * Creates a merged array c such that c.length = a.length + b.length, and all the elements of b
     * appear in c in the original order, but only after all elements of a (again, in the original
     * order). For example, merge([1,2,3], [4,5]) yields the array [1,2,3,4,5].
     * @param a: the first of the two arrays to be merged
     * @param b: the second of the two arrays to be merged
     * @return c: the merged array
     */
    public static int[] merge(int[] a, int[] b){
        int[] c = new int[a.length + b.length];
        int index = 0;
        for(int i=0; i<a.length; i++){
            c[i] = a[i];
        }
        for(int j=a.length; j<c.length; j++, index++){
            c[j] = b[index];
        }
        return c;
    }


}


