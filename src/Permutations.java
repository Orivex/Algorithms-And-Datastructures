import java.lang.reflect.Array;
import java.util.*;

public class Permutations {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        System.out.println(permute(nums));
    }

    public static void backtrack(List<List<Integer>> resultList, int[] numsList, ArrayList<Integer> tempList) {

        if(tempList.size() == numsList.length) {
            resultList.add(new ArrayList<>(tempList));
            System.out.println("RESULT " + tempList);
            return;
        }

        for(int number : numsList) {

            System.out.println("C" + number);

            if(tempList.contains(number)) {
                System.out.println("continue " + number);
                continue;
            }

            tempList.add(number);
            System.out.println("adding " + number);


            System.out.println("BEFORE " + tempList);
            backtrack(resultList, numsList, tempList);
            System.out.println("AFTER " + tempList);

            System.out.println("removing " + tempList.size());
            tempList.remove(tempList.size() - 1);
        }
    }
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        ArrayList<Integer> remaining = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            remaining.add(nums[i]);
        }

        backtrack(result,  nums, new ArrayList<Integer>());
        return result;
    }
}
