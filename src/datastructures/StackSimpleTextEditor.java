package datastructures;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by bigwood928 on 8/21/2016.
 */
public class StackSimpleTextEditor {

    public static void main(String [] args) {
        Scanner input = new Scanner(System.in);
        int numOps = Integer.parseInt(input.nextLine());
        Stack<String> undos = new Stack<String>();
        String[] line;
        for (int i = 0; i < numOps; i++) {
            line = input.nextLine().split(" ");
            String top = "";
            if (!undos.isEmpty()) {
                top = undos.peek();
            }
            int operation = Integer.parseInt(line[0]);
            switch (operation) {
                case 1:
                    undos.push(top + line[1]);
                    break;
                case 2:
                    undos.push(top.substring(0, top.length() - Integer.parseInt(line[1])));
                    break;
                case 3:
                    System.out.println(top.charAt(Integer.parseInt(line[1]) - 1));
                    break;
                case 4:
                    undos.pop();
                    break;
            }
        }
    }
}