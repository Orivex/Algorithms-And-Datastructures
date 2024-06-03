import java.util.Arrays;

public class InsertionSort {
    public static void main(String[] args) {
        int[] array = {2, 3, 1, 7, 3, 7, 8, 1, 23, 5, 7, 3,8, 1, 3};

        printArray(increasing(array));
        printArray(decreasing(array));
    }

    static int[] increasing(int[] array) {
        for (int i = 1; i < array.length; i++) {

            int currentElement = array[i];
            int j = i-1;

            while (j >= 0 && array[j] > currentElement) {
                array[j+1] = array[j];
                j--;
            }

            array[j+1] = currentElement;
        }

        return array;
    }

    static int[] decreasing(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int currentElement = array[i];

            int j = i-1;

            while (j >= 0 && array[j] < currentElement) {
                array[j+1] = array[j];
                j--;
            }

            array[j+1] = currentElement;
        }

        return array;
    }
    static void printArray(int[] array) {
        System.out.println(Arrays.toString(array));
    }
}
