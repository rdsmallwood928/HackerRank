package algorithms.sorting;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by robert.smallwood on 9/12/16.
 */
public class IntroToTutorialChallenges {
    public static void main(String [] args) {
        Scanner input = new Scanner(System.in);
        int value = input.nextInt();
        int numValues = input.nextInt();
        ArrayList<Integer> values = new ArrayList<>();
        for(int i = 0; i<numValues;i++) {
            if (input.nextInt() == value) {
                System.out.print(i);
            }
        }
    }
}
