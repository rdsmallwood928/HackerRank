package datastructures;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by bigwood928 on 8/21/2016.
 */
public class StacksEqualStacks {

    public static void main(String [] args) {
        Scanner input = new Scanner(System.in);

        List<Stack<Integer>> stacks = new ArrayList<>();
        List<Integer> currentStackHeights = new ArrayList<>();
        for(int i=0; i<3; i++) {
            stacks.add(new Stack<Integer>());
            currentStackHeights.add(0);
        }
        input.nextLine();
        for (int stackIndex=0; stackIndex<3; stackIndex++) {
            String[] cylinders = input.nextLine().split(" ");
            int height = 0;
            for(int i=cylinders.length-1; i>=0; i--) {
                int cylinder = Integer.parseInt(cylinders[i]);
                height = height + cylinder;
                stacks.get(stackIndex).push(cylinder);
            }
            currentStackHeights.set(stackIndex, height);
        }

        boolean haveEqualHeights = false;
        while(!haveEqualHeights) {
            int tallestIndex = checkForEqualHeights(currentStackHeights);
            if(tallestIndex > -1) {
                int remove = stacks.get(tallestIndex).pop();
                currentStackHeights.set(tallestIndex, currentStackHeights.get(tallestIndex)-remove);
            } else {
                haveEqualHeights = true;
            }
        }
        System.out.println(currentStackHeights.get(0));
    }

    private static int checkForEqualHeights(List<Integer> currentStackHeights) {
        int height = currentStackHeights.get(0);
        int tallest = 0;
        int tallestIndex = 0;
        boolean allEqual = true;
        for(int i=0; i<currentStackHeights.size(); i++) {
            if(height != currentStackHeights.get(i)) {
                allEqual = false;
            }
            if(currentStackHeights.get(i) > tallest) {
                tallestIndex = i;
                tallest =  currentStackHeights.get(i);
            }
        }
        if(allEqual) {
            return -1;
        } else {
            return tallestIndex;
        }
    }
}
