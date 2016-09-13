package datastructures;

import java.util.Scanner;

/**
 * Created by bigwood928 on 8/14/2016.
 */
public class HourglassSum {

    public static void main(String[] args) {
        int[][] hourglassArray = new int[6][6];
        Scanner input = new Scanner(System.in);
        for(int i=0; i<6; i++) {
            for(int j=0; j<6; j++) {
                hourglassArray[i][j] = input.nextInt();
            }
        }
        int biggestHourglass = Integer.MIN_VALUE;
        for(int i=0; i<4; i++) {
            for(int j=0;j<4;j++) {
                int hourglassSum = calculateHourglass(i,j,hourglassArray);
                if(biggestHourglass < hourglassSum) {
                    biggestHourglass = hourglassSum;
                };
            }
        }
        System.out.println(biggestHourglass);
    }

    public static int calculateHourglass(int i,int j, int[][] hourglassArray) {
        int sum = 0;
        for(int y=0; y<3;y++) {
            if(y!=1) {
                sum = sum + hourglassArray[i+y][j] + hourglassArray[i+y][j+1] + hourglassArray[i+y][j+2];
            } else {
                sum = sum + hourglassArray[i+y][j+1];
            }
        }
        return sum;
    }
}
