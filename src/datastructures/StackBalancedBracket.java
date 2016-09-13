package datastructures;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by bigwood928 on 8/16/2016.
 */
public class StackBalancedBracket {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Stack<Character> brackets = new Stack<>();
        int numInputs = Integer.parseInt(input.nextLine());
        Map<Character, Character> findPair = new HashMap<>();
        findPair.put('}', '{');
        findPair.put(']', '[');
        findPair.put(')', '(');
        while(input.hasNext()) {
            String bracketString = input.nextLine();
            boolean isBalanced = true;
            for(int i=0; i<bracketString.length(); i++) {
                char stackChar = ' ';
                if (isBalanced) {
                    switch (bracketString.charAt(i)) {
                        case '[':
                        case '{':
                        case '(':
                            brackets.push(bracketString.charAt(i));
                            break;
                        case ']':
                        case '}':
                        case ')':
                            isBalanced = isBalanced(bracketString.charAt(i), brackets, findPair);
                            break;
                    }
                }
            }
            if(isBalanced && brackets.isEmpty()) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
            brackets.clear();
        }
    }

    private static boolean isBalanced(Character current, Stack<Character> brackets, Map<Character, Character> pairMap) {
        boolean isBalanced = true;
        if(!brackets.isEmpty()) {
            if (brackets.pop()!= pairMap.get(current)) {
                isBalanced = false;
            }
        } else {
            isBalanced = false;
        }
        return isBalanced;
    }
}
