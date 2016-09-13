import java.util.*;
import java.util.jar.Pack200;

/**
 * Created by bigwood928 on 8/22/2016.
 */
public class StacksPoisonousPlants {

    public static void main(String [] args) {
        Scanner input = new Scanner(System.in);
        int numPlants = input.nextInt();
        Stack<Integer> plants = new Stack<>();
        Stack<Integer> index = new Stack<>();
        Stack<Integer> dayOfDeath = new Stack<>();
        //Just leaving killerArray around for debugging
        List<Integer> killerArray = new ArrayList<>();

        int maxDays = 0;
        killerArray.add(-1);
        plants.push(input.nextInt());
        index.push(0);
        dayOfDeath.push(-1);
        for(int i=1;i<numPlants;i++) {
            int plant = input.nextInt();
            if(plant <= plants.peek()) {
                int dayTaken = findKiller(plants, index,  plant, dayOfDeath);
                if(!index.isEmpty()) {
                    killerArray.add(index.peek());
                } else {
                    killerArray.add(-1);
                    dayTaken=-1;
                }
                if(dayTaken > maxDays) {
                    maxDays = dayTaken;
                }
                plants.push(plant);
                index.push(i);
                dayOfDeath.push(dayTaken);
            } else {
                plants.push(plant);
                index.push(i);
                dayOfDeath.push(1);
                killerArray.add(i-1);
                if(1 > maxDays) {
                    maxDays = 1;
                }
            }
        }
        System.out.println(maxDays);
    }

    private static int findKiller(Stack<Integer> plants, Stack<Integer> index, int plant, Stack<Integer> dayOfDeath) {
        boolean foundKiller = false;
        int currentDay=1;
        while(!plants.isEmpty() && !foundKiller) {
            if(plant <= plants.peek()) {
                //This plant will protect the current plant at least until its day of death
                if(dayOfDeath.peek() > currentDay) {
                    currentDay = dayOfDeath.peek();
                }
                plants.pop();
                index.pop();
                dayOfDeath.pop();
            } else {
                int potentialKillerDayOfDeath = dayOfDeath.peek();
                //Its now the day after the latest day of death...so check one extra
                if (potentialKillerDayOfDeath >= currentDay+1 || potentialKillerDayOfDeath == -1) {
                    foundKiller = true;
                } else {
                    plants.pop();
                    index.pop();
                    dayOfDeath.pop();
                }
            }
        }
        return currentDay+1;
    }
}
