import java.util.Scanner;

/**
 * Created by bigwood928 on 8/14/2016.
 */
public class ArraysDS {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int length = input.nextInt();
        int[] reverseThis = new int[length];
        String outPut = "";
        for(int i=0; i<length; i++) {
            outPut = input.nextInt() + " " + outPut;
        }
        System.out.println(outPut.trim());
    }
}
