package algorithms;

import java.util.*;

/**
 * Created by robert.smallwood on 9/11/16.
 */
public class DivisibleSumPairs {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int k = input.nextInt();
        HashMap<Integer, List<Integer>> modBuckets = new HashMap<>();
        for(int i=0; i<k;i++) {
            modBuckets.put(i, new ArrayList<Integer>());
        }
        for(int i=0; i<n; i++) {
            Integer number = input.nextInt();
            modBuckets.get(number%k).add(number);
        }
        HashSet<Integer> checkedValues = new HashSet<>();
        int pairs = 0;
        for(Integer key : modBuckets.keySet()) {
            if(!checkedValues.contains(key)) {
                if(k-key != key && key != 0) {
                    pairs = pairs + (modBuckets.get(key).size() * modBuckets.get(k - key).size());
                    checkedValues.add(key);
                    checkedValues.add(k - key);
                } else {
                    pairs = pairs + (modBuckets.get(key).size()*(modBuckets.get(key).size()-1))/2;
                    checkedValues.add(key);
                }
            }
        }
        System.out.println(pairs);
    }
}
