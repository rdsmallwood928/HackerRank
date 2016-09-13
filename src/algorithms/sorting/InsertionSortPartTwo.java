package algorithms.sorting;

import java.util.Scanner;

/**
 * Created by robert.smallwood on 9/12/16.
 */
public class InsertionSortPartTwo {

    public static void insertionSortPart2(int[] ar) {
        for(int i=1; i<ar.length; i++) {
            if(ar[i] < ar[i-1]) {
                int insertionVal = ar[i];
                int currentIndex = i;
                while(currentIndex > 0 && ar[currentIndex-1] > insertionVal) {
                    ar[currentIndex] = ar[currentIndex-1];
                    currentIndex--;
                }
                ar[currentIndex] = insertionVal;
            }
            printArray(ar);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int s = in.nextInt();
        int[] ar = new int[s];
        for(int i=0;i<s;i++){
            ar[i]=in.nextInt();
        }
        insertionSortPart2(ar);

    }
    private static void printArray(int[] ar) {
        for(int n: ar) {
            System.out.print(n+" ");
        }
        System.out.println("");
    }
}
