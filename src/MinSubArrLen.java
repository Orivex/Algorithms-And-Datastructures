import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinSubArrLen {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(minSubArrLen(arr, 11));
    }

    public static int minSubArrLen(int[] nums, int target) {
        int result = Integer.MAX_VALUE;
        int left = 0;
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            while(sum >= target) {
                result = Math.min(result, i+1 - left);

                sum -= nums[left];
                left++;
            }
        }

        return result != Integer.MAX_VALUE ? result: 0;
    }
}
