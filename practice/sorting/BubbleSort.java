package practice.sorting;

public class BubbleSort {
    static void bubbleSortArray(int array[], int length) {
        if (length < 2) {
            return;
        }

        for (int pass = 0; pass <= length - 1; pass++) {
            boolean atleastOneSwapHappened = false;
            for(int iterator = 0; iterator <= length - pass - 2; iterator ++) {
                if (array[iterator] > array[iterator + 1]) {
                    atleastOneSwapHappened = true;
                    int swapTemp = array[iterator];
                    array[iterator] = array[iterator + 1];
                    array[iterator + 1] = swapTemp;
                }
            }
            if (!atleastOneSwapHappened) {
                break;
            }
        }
    }

    public static void main(String arg[]) {
        int[] array = { 6, 8, 9, 4, 1, 5 };
        int length = 6;
        bubbleSortArray(array, length);

        for (int index = 0; index < length; index++)
            System.out.print(array[index] + " ");
    }

}
