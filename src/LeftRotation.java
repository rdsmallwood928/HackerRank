import java.util.Scanner;

/**
 * Created by bigwood928 on 8/14/2016.
 */
public class LeftRotation {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int size = input.nextInt();
        int numRotation = input.nextInt();
        int[] rotateMe = new int[size];
        for(int i=0;i<size;i++) {
            rotateMe[i] = input.nextInt();
        }
        int startIndex = numRotation % size;
        String rotatedString = "";
        for(int i=0;i<size;i++) {
            if(i+startIndex < size) {
                System.out.print(rotatedString + rotateMe[i + startIndex] + " ");
            } else {
                System.out.print(rotatedString + rotateMe[(i+startIndex) - size] + " ");
            }
        }
    }
}
