package datastructures;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by bigwood928 on 8/14/2016.
 */
public class DynamicArray {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numSeqs = input.nextInt();
        int numQueries = input.nextInt();
        int lastAns = 0;
        ArrayList<ArrayList<Integer>> sequences = new ArrayList<ArrayList<Integer>>(numSeqs);
        for(int i=0; i<numSeqs; i++) {
            sequences.add(new ArrayList<Integer>());
        }
        for(int i=0;i<numQueries;i++) {
            int queryType = input.nextInt();
            int bitwise = input.nextInt();
            int appendMe = input.nextInt();
            if(queryType == 1) {
                int sequence = (bitwise ^ lastAns) % numSeqs;
                sequences.get(sequence).add(appendMe);
            } else {
                int sequence = (bitwise ^ lastAns) % numSeqs;
                lastAns = sequences.get(sequence).get(appendMe % sequences.get(sequence).size());
                System.out.println(lastAns);
            }
        }
    }
}
