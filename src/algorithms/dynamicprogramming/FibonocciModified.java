package algorithms.dynamicprogramming;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by robert.smallwood on 9/13/16.
 */
public class FibonocciModified {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        BigInteger t0 = new BigInteger(input.next());
        BigInteger t1 = new BigInteger(input.next());
        int n = input.nextInt();
        BigInteger nextEntry = new BigInteger("0");
        for(int i=2; i<n;i++) {
            nextEntry = t0.add(t1.multiply(t1));
            t0=t1;
            t1=nextEntry;
        }
        System.out.println(nextEntry);
    }
}
