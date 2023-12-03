package practice.sorting;

public class InsertionSort {

    /*
     * Partition the array into two halves:
     * LHS - sorted
     * RHS - unsorted
     */
    static void insertionSortArray(int array[], int length) {
        for (int currentIndex = 0; currentIndex <= length - 1; currentIndex++) {
            int currentValue = array[currentIndex];
            int hole = currentIndex;

            // Keep shifting the hole to left if the value at the left of the hole is
            // greater than the currentValue
            while (hole > 0 && array[hole - 1] > currentValue) {
                array[hole] = array[hole - 1];
                hole--;
            }
            // Put the currentValue at the correct place (ie the final position of hole)
            array[hole] = currentValue;
        }
    }

    public static void main(String args[]) {
        int[] array = { 6, 8, 9, 4, 1, 5 };
        int length = 6;
        insertionSortArray(array, length);

        for (int index = 0; index < length; index++)
            System.out.print(array[index] + " ");
    }

}
