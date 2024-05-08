import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SlidingWindow {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        System.out.println(sumOfArray(arr, 3));
    }

    public static int sumOfArray(int[] arr, int k) {
        int windowSum = 0;
        int first = 0;
        int result = 0;

        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }

        result += windowSum;

        first = 1;
        while(first < arr.length - k + 1) {
            windowSum -= arr[first-1];
            windowSum += arr[first+k-1];
            result += windowSum;
            first++;
        }

        return result;

    }
}