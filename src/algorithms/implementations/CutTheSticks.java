package algorithms.implementations;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Created by robert.smallwood on 9/12/16.
 */
public class CutTheSticks {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numSticks = input.nextInt();
        PriorityQueue<Integer> sticks = new PriorityQueue<>();
        for (int i = 0; i < numSticks; i++) {
            sticks.add(input.nextInt());
        }
        int currentCut=0;
        while(!sticks.isEmpty()) {
            System.out.println(sticks.size());
            currentCut = cut(sticks, currentCut);
        }
    }

    public static int cut(PriorityQueue<Integer> sticks, int currentCut) {
        int newCut = currentCut + (sticks.peek()-currentCut);
        while(!sticks.isEmpty() && sticks.peek() - newCut <= 0) {
            sticks.poll();
        }
        return newCut;
    }
}
