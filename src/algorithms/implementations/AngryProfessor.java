package algorithms.implementations;

import java.util.Scanner;

/**
 * Created by robert.smallwood on 9/12/16.
 */
public class AngryProfessor {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numTestCases = input.nextInt();
        for(int i = 0; i < numTestCases; i++){
            int numStudents = input.nextInt();
            int numStudentsNeeded = input.nextInt();
            int studentsOnTime=0;
            for(int j=0; j < numStudents; j++){
                if(input.nextInt() <= 0) {
                    studentsOnTime++;
                }
            }
            System.out.println((studentsOnTime >= numStudentsNeeded) ? "NO" : "YES");
            }
        }
}
