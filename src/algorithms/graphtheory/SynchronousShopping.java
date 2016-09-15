package algorithms.graphtheory;

import java.util.*;

/**
 * Created by robert.smallwood on 9/13/16.
 */
public class SynchronousShopping {
    public static void main(String[] main) {
        //Read it all in and initialize...
        Scanner input = new Scanner(System.in);
        int numShoppingCenters = input.nextInt();
        int numRoads = input.nextInt();
        int numTypesOfFish = input.nextInt();
        Bitville bitville = new Bitville(numTypesOfFish);
        for(int i=0; i<numShoppingCenters; i++) {
            List<Integer> fishSold = new ArrayList<>();
            int numTypesOfFishSold = input.nextInt();
            for(int j=0;j<numTypesOfFishSold;j++) {
                fishSold.add(input.nextInt());
            }
            bitville.addShoppingCenter(new ShoppingCenter(fishSold));
        }
        for(int i=0;i<numRoads;i++) {
            int shoppingCenterA = input.nextInt()-1;
            int shoppingCenterB = input.nextInt()-1;
            int time = input.nextInt();
            bitville.addRoad(shoppingCenterA, shoppingCenterB, time);
        }
        System.out.println(bitville.goShopping());
    }
}

class Bitville {

    private List<ShoppingCenter> shoppingCenters = new ArrayList<>();
    private int fishTypes = 0;

    public Bitville(int fishTypes) {
        this.fishTypes = fishTypes;
    }

    public void addShoppingCenter(ShoppingCenter newCenter) {
        shoppingCenters.add(newCenter);
    }

    public void addRoad(int shoppingCenterA, int shoppingCenterB, int time) {
        ShoppingCenter a = shoppingCenters.get(shoppingCenterA);
        ShoppingCenter b = shoppingCenters.get(shoppingCenterB);
        a.addRoad(b, time);
        b.addRoad(a, time);
    }

    public int goShopping() {
        Set<Integer> purchasedFish = new HashSet<>();
        purchasedFish.addAll(shoppingCenters.get(0).getFistSoldHere());
        Cat bigCat = new Cat(shoppingCenters.get(0));
        Cat littleCat = new Cat(shoppingCenters.get(0));
        boolean littleCatShops = true;
        while(purchasedFish.size() < this.fishTypes) {
            if(littleCatShops) {
                littleCat.findNextMove(purchasedFish);
                littleCatShops = false;
            } else {
                bigCat.findNextMove(purchasedFish);
                littleCatShops = true;
            }
        }
        bigCat.getTimeToTheLastShop(shoppingCenters);
        littleCat.getTimeToTheLastShop(shoppingCenters);
        return (bigCat.getTimeSpentShopping() > littleCat.getTimeSpentShopping()) ? bigCat.getTimeSpentShopping() :
                littleCat.getTimeSpentShopping();
    }
 }

class ShoppingCenter {
    private List<Road> roads = new ArrayList<>();
    private Set<Integer> fishSoldHere = new HashSet<>();

    public ShoppingCenter(Collection<Integer> fishSold) {
        fishSoldHere.addAll(fishSold);
    }

    public void addRoad(ShoppingCenter to, int time) {
        roads.add(new Road(to, time));
        Collections.sort(roads);
    }

    public List<Road> getRoads() {
        return roads;
    }

    public Set<Integer> getFistSoldHere() {
        return fishSoldHere;
    }

    public int getTimeTo(ShoppingCenter center) {
        for(Road road : this.roads) {
            if(center.equals(road.getTo())) {
                return road.getTime();
            }
        }
        return Integer.MIN_VALUE;
    }

    public boolean hasANeededFish(Set<Integer> purchasedFish) {
        for(Integer fishType : fishSoldHere) {
            if(!purchasedFish.contains(fishType)) {
                return true;
            }
        }
        return false;
    }
}

class Road implements Comparable<Road> {
    private int time;
    private ShoppingCenter to;

    public Road(ShoppingCenter to, int time) {
        this.to = to;
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public ShoppingCenter getTo() {
        return to;
    }

    @Override
    public int compareTo(Road other) {
        return this.time - other.getTime();
    }
}

class Cat {
    private ShoppingCenter location = null;
    private int timeSpentShopping = 0;

    public Cat(ShoppingCenter start) {
        location = start;
    }

    public int getTimeSpentShopping() {
        return timeSpentShopping;
    }

    public void findNextMove(Set<Integer> purchasedFish) {
        boolean foundSomeFish = false;
        Queue<List<Road>> roadsToCheck = new ArrayDeque<>();
        for(Road road : location.getRoads()) {
            ArrayList<Road> path = new ArrayList<>();
            path.add(road);
            roadsToCheck.add(path);
        }
        HashSet<ShoppingCenter> visitedCenter = new HashSet<>();
        while(!roadsToCheck.isEmpty()  && !foundSomeFish) {
            List<Road> path = roadsToCheck.poll();
            Road road = path.get(path.size()-1);
            if (road.getTo().hasANeededFish(purchasedFish)) {
                purchasedFish.addAll(road.getTo().getFistSoldHere());
                location = road.getTo();
                for(Road stop : path) {
                    timeSpentShopping = timeSpentShopping + stop.getTime();
                }
                foundSomeFish = true;
            } else {
                for(Road anotherRoad : road.getTo().getRoads()) {
                    if(!visitedCenter.contains(anotherRoad.getTo())) {
                        visitedCenter.add(anotherRoad.getTo());
                        ArrayList<Road> anotherPath = new ArrayList<>(path);
                        anotherPath.add(anotherRoad);
                        roadsToCheck.add(anotherPath);
                    }
                }
            }
        }
    }

    public void getTimeToTheLastShop(List<ShoppingCenter> centers) {
        List<ShoppingCenter> unsettledShops = new ArrayList<>();
        List<ShoppingCenter> settledShops = new ArrayList<>();
        HashMap<ShoppingCenter, Integer> distances = new HashMap<>();
        HashMap<ShoppingCenter, ShoppingCenter> predecessors = new HashMap<ShoppingCenter, ShoppingCenter>();
        for(ShoppingCenter center : centers) {
            distances.put(center, Integer.MAX_VALUE);
        }
        unsettledShops.add(location);
        distances.put(location, 0);
        while(!unsettledShops.isEmpty()) {
            ShoppingCenter shortest = getMinimum(unsettledShops, distances);
            settledShops.add(shortest);
            unsettledShops.remove(shortest);
            findMinimalDistance(shortest, distances, unsettledShops, predecessors);
        }
        int timeUsed = 0;
        ShoppingCenter stop = centers.get(centers.size()-1);
        while (predecessors.get(stop) != null) {
            timeUsed = timeUsed + stop.getTimeTo(predecessors.get(stop));
            stop = predecessors.get(stop);
        }
        this.timeSpentShopping = timeSpentShopping + timeUsed;
    }

    private void findMinimalDistance(ShoppingCenter shortest, HashMap<ShoppingCenter, Integer> distances,
                                     List<ShoppingCenter> unsettled, HashMap<ShoppingCenter, ShoppingCenter> predecessors) {
        List<Road> roads = shortest.getRoads();
        for(Road road : roads) {
            if (distances.get(road.getTo()) > distances.get(shortest) + road.getTime()) {
                distances.put(road.getTo(), distances.get(shortest) + road.getTime());
                unsettled.add(road.getTo());
                predecessors.put(road.getTo(), shortest);
            }
        }
    }

    private ShoppingCenter getMinimum(List<ShoppingCenter> centers, HashMap<ShoppingCenter, Integer> distances) {
        ShoppingCenter minimum = null;
        for(ShoppingCenter center : centers) {
            if(minimum == null) {
                minimum = center;
            } else {
                if(distances.get(center) < distances.get(minimum)) {
                    minimum = center;
                }
            }
        }
        return minimum;
    }
}
