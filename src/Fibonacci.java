import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fibonacci {
    public static void main(String[] args) {
        int n = 5;
        int[] arr = new int[n];

        for(int i = 0; i < arr.length; i++) {
            arr[i] = -1;
        }

        System.out.println(fib1(n)); //slow
        System.out.println(fib2(n)); //fast
        System.out.println(fib3(n)); //fast and saves memory
    }

    //O(2^n)
    public static int fib1(int n) {
        if(n <= 1) {
            return n;
        }

        return fib1(n-2) + fib1(n-1);
    }

    //O(n)
    public static int fib2(int n) {
        int[] arr = new int[n];
        arr[0] = 1;
        arr[1] = 1;

        for (int i = 2; i < n; i++) {
            arr[i] = arr[i-2] + arr[i-1];
        }

        return arr[n-1];
    }

    //O(n) Time & O(1) Space
    public static int fib3(int n) {
        int first = 1;
        int second = 1;
        int result = 0;

        for (int i = 2; i < n; i++) {
            result = first + second;
            first = second;
            second = result;
        }

        return result;
    }
}
