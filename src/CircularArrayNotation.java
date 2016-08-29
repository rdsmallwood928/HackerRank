import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by bigwood928 on 8/13/2016.
 */

public class CircularArrayNotation {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int arrayLen = input.nextInt();
        int numArrayRotations = input.nextInt();
        int numQueries = input.nextInt();
        int[] array = new int[arrayLen];
        for (int i = 0; i < arrayLen; i++) {
            array[i] = input.nextInt();
        }
        int startIndex = arrayLen - (numArrayRotations % arrayLen);
        for(int i=0; i<numQueries;i++) {
            int query = input.nextInt();
            System.out.println(array[(startIndex + query) % arrayLen]);
        }
    }
}
