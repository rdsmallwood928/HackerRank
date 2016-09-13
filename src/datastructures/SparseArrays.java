package datastructures;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by bigwood928 on 8/14/2016.
 */
public class SparseArrays {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int size = Integer.parseInt(input.nextLine());
        HashMap<String, Integer> occurances = new HashMap<String, Integer>();
        for(int i=0;i<size;++i){
            String aString = input.nextLine();
            if(occurances.get(aString) == null) {
                occurances.put(aString, 1);
            } else {
                int numOccurances = occurances.get(aString);
                numOccurances = numOccurances +1;
                occurances.put(aString, numOccurances);
            }
        }
        int numQueries = Integer.parseInt(input.nextLine());
        for(int i=0;i<numQueries;i++) {
            String aQuery = input.nextLine();
            if(occurances.get(aQuery)==null){
                System.out.println(0);
            } else {
                System.out.println(occurances.get(aQuery));
            }
        }
    }
}
