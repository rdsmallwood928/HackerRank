package algorithms;

import java.util.Scanner;

/**
 * Created by robert.smallwood on 9/11/16.
 */
public class Kangaroo {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int x1 = in.nextInt();
        int v1 = in.nextInt();
        int x2 = in.nextInt();
        int v2 = in.nextInt();
        if ((v1>v2 && (x2-x1)%(v1-v2)==0)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
