import com.sun.source.tree.Tree;

import java.util.*;

public class Heaps {
    public static void main(String[] args) {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            Random r = new Random();
            arr[i] = r.nextInt(0, 10);
        }

        heapsort(arr);
    }

    public static void heapsort(int[] arr) {
        buildMaxHeap(arr);

        for(int i = 0; i < arr.length; i++) {
            int lastElementIdx = arr.length-i-1;

            int rootVal = arr[0];
            arr[0] = arr[lastElementIdx];
            arr[lastElementIdx] = rootVal;

            heapify(0, lastElementIdx, arr);
        }

        System.out.println("\nSorted");

        for (int k = 0; k < arr.length; k++) {
            System.out.print(arr[k] + " ");
        }
    }

    public static void buildMaxHeap(int[] arr) {
        for (int i = (int)Math.floor(arr.length / 2 - 1); i >= 0 ; i--) {
            heapify(i, arr.length, arr);
        }

        for (int k = 0; k < arr.length; k++) {
            System.out.print(arr[k] + " ");
        }
    }
    public static void heapify(int idx, int n, int[] arr) {
        int largestIdx = idx;
        int leftChildIdx = 2*idx+1;
        int rightChildIdx = 2*idx+2;

        if(leftChildIdx < n && arr[leftChildIdx] > arr[largestIdx]) {
            largestIdx = leftChildIdx;
        }

        if(rightChildIdx < n && arr[rightChildIdx] > arr[largestIdx]) {
            largestIdx = rightChildIdx;
        }

        if(largestIdx != idx) {
            int currentIdxVal = arr[idx];
            arr[idx] = arr[largestIdx];
            arr[largestIdx] = currentIdxVal;

            heapify(largestIdx, n, arr);
        }

    }
}
