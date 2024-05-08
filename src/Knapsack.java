import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Knapsack {
    public static void main(String[] args) {
        List<Thing> things = new ArrayList<>(Arrays.asList(
                new Thing(4, 6), new Thing(3, 4), new Thing(8, 10), new Thing(1, 5)));

        divisible(things);

    }

    public static void divisible(List<Thing> things) {
        //profit geteilt durch weight
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < things.size(); i++) {
            list.add(things.get(i).profit / things.get(i).weight);
        }

        double maxWeight = 10;
        double knapsack = 0;
        double profit = 0;

        while(knapsack < maxWeight && list.size() > 0) {
            int highestIdx = findHighestNumberIndex(list);
            Thing nextThing = things.get(highestIdx);
            if((knapsack + nextThing.weight) <= maxWeight) {
                knapsack += nextThing.weight;
                profit += nextThing.profit;
                list.remove(highestIdx);
                things.remove(highestIdx);
                System.out.println("adding.. profit " + nextThing.profit + " and weight " + nextThing.weight);
                System.out.println("sack " + knapsack + " profit " + profit);
                System.out.println("Remaining list: " + list);
            }
            else {
                double fraction = ((maxWeight - knapsack) / nextThing.weight);
                knapsack += fraction * nextThing.weight;
                profit += fraction * nextThing.profit;
                System.out.println("----END----");
                break;
            }
        }

        System.out.println("Knapsack weight: " + knapsack + "\nProfit: " + profit);
    }

    public static int findHighestNumberIndex(List<Integer> list) {
        int maxidx = 0;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i) > list.get(maxidx)) {
                maxidx = i;
            }
        }
        return maxidx;
    }

    public static class Thing {
        int profit = 5;
        int weight = 5;
        public Thing(int profit, int weight) {
            this.profit = profit;
            this.weight = weight;
        }
    }
}
