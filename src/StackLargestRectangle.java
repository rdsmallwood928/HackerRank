import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by bigwood928 on 8/16/2016.
 */
public class StackLargestRectangle {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numBuildings = Integer.parseInt(input.nextLine());
        ArrayList<Integer> buildings = new ArrayList<>();
        for(String building : input.nextLine().split(" ")){
            buildings.add(Integer.parseInt(building));
        }
        Stack<Integer> positions = new Stack<>();
        Stack<Integer> height = new Stack<>();
        int biggestRect = 0;
        for(int i=0; i<buildings.size();i++) {
            if(height.isEmpty() || buildings.get(i) > height.peek()) {
                height.push(buildings.get(i));
                positions.push(i);
            } else if(buildings.get(i) < height.peek()){
                int positionPop = 0;
                while(!height.isEmpty() && buildings.get(i) < height.peek()) {
                    int heightPop = height.pop();
                    positionPop = positions.pop();
                    int currentArea = heightPop * (i-positionPop);
                    if(currentArea > biggestRect) {
                        biggestRect = currentArea;
                    }
                }
                height.push(buildings.get(i));
                positions.push(positionPop);
            }
        }
        while(!height.isEmpty()) {
            int storedHeight = height.pop();
            int storedPosition = positions.pop();
            int currentRect = storedHeight * ((buildings.size()) - storedPosition);
            if(currentRect > biggestRect) {
                biggestRect = currentRect;
            }
        }
        System.out.println(biggestRect);
    }
}
