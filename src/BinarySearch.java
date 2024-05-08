import java.util.*;

public class BinarySearch {
    public static void main(String[] args) {
            List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 6, 8, 10, 22, 42, 45, 84));

            search_recursive(numbers, 0,46);
        }

        public static void search_recursive(List<Integer> list, int midPoint, int target) {

            if(list.size() == 1) {
                System.out.println("Target not found");
                return;
            }

            System.out.println("Remaining List: " + list);

            midPoint = (int)Math.floor(list.size() / 2);
            System.out.println("Midpoint: " + midPoint);

            List<Integer> remainingList = new ArrayList<Integer>();

            if(list.get(midPoint) == target) {
                System.out.println("Target " + target + " found");
                return;
            }
            else if(target < list.get(midPoint)) {
                remainingList = slice(list, 0, midPoint - 1);
            }
            else if (target > list.get(midPoint)) {
                remainingList = slice(list, midPoint, list.size() - 1);
            }

            search_recursive(remainingList, midPoint, target);
        }

        public static List<Integer> slice(List<Integer> list, int first, int last) {
            List<Integer> newList = new ArrayList<>();

            for(int i = first; i<=last; i++) {
                newList.add(list.get(i));
            }

            return newList;
        }

    }