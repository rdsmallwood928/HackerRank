package datastructures;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by bigwood928 on 8/16/2016.
 */
public class StackMaximumElement {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Stack<Integer> stack = new Stack<>();
        int numQueries = input.nextInt();
        int max = Integer.MIN_VALUE;
        while(input.hasNext()) {
            String[] query = input.nextLine().split(" ");
            if(!query[0].isEmpty()) {
                switch (Integer.parseInt(query[0])) {
                    case 1:
                        int pushMe = Integer.parseInt(query[1]);
                        stack.push(pushMe);
                        if(max < pushMe) {
                            max = pushMe;
                        }
                        break;
                    case 2:
                        int delete = stack.pop();
                        if(delete >= max) {
                            max = Integer.MIN_VALUE;
                            for(int isMax : stack) {
                                if(isMax > max) {
                                    max = isMax;
                                }
                            }
                        }

                        break;
                    case 3:
                        System.out.println(max);
                        break;
                }
            }
        }
    }
}
