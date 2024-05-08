import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 2, 5, 9};

        mergesort(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void mergesort(int[] arr) {

        if(arr.length == 0 || arr.length == 1) {
            return;
        }

        int midpoint = (int)Math.floor(arr.length / 2);
        int[] leftHalf = new int[midpoint];
        int[] rightHalf = new int[arr.length - midpoint];

        for(int i = 0; i < midpoint; i++) {
            leftHalf[i] = arr[i];
        }

        for(int i = midpoint; i < arr.length; i++) {
            rightHalf[i-midpoint] = arr[i];
        }

        mergesort(leftHalf);
        mergesort(rightHalf);
        merge(arr, leftHalf, rightHalf);
    }

    public static int[] merge(int[] arr, int[] leftHalf, int[] rightHalf) {
        int k = 0;
        int i = 0;
        int j = 0;

        while(i < leftHalf.length && j < rightHalf.length) {
            if(leftHalf[i] < rightHalf[j]) {
                arr[k] = leftHalf[i];
                i++;
            }
            else {
                arr[k] = rightHalf[j];
                j++;
            }
            k++;
        }

        return arr;
    }
}
