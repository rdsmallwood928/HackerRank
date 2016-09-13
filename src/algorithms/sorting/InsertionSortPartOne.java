package algorithms.sorting;

import java.util.Scanner;

/**
 * Created by robert.smallwood on 9/12/16.
 */
public class InsertionSortPartOne {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int s = in.nextInt();
        int[] ar = new int[s];
        for(int i=0;i<s;i++){
            ar[i]=in.nextInt();
        }
        insertIntoSorted(ar);
    }


    private static void printArray(int[] ar) {
        for(int n: ar){
            System.out.print(n+" ");
        }
        System.out.println("");
    }

    public static void insertIntoSorted(int[] ar) {
        int insertionVal = ar[ar.length-1];
        int currentIndex = ar.length-1;
        while(currentIndex > 0 && ar[currentIndex-1] > insertionVal) {
            ar[currentIndex] = ar[currentIndex-1];
            printArray(ar);
            currentIndex--;
        }
        ar[currentIndex] = insertionVal;
        printArray(ar);
    }
}
