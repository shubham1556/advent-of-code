package practice.sorting;

public class SelectionSort {

    static void selectionSortArray(int array[], int length) {
        if (length < 2) {
            return;
        }

        for (int currentSelection = 0; currentSelection <= length - 2; currentSelection++) {
            int minIndex = currentSelection;

            for (int searchIndex = currentSelection + 1; searchIndex <= length - 1; searchIndex++) {
                if (array[searchIndex] < array[minIndex]) {
                    minIndex = searchIndex;
                }
            }
            if (minIndex != currentSelection) {
                int swapTemp = array[currentSelection];
                array[currentSelection] = array[minIndex];
                array[minIndex] = swapTemp;
            }
        }
    }

    public static void main(String args[]) {
        int[] array = { 6, 8, 9, 4, 1, 5 };
        int length = 6;
        selectionSortArray(array, length);

        for (int index = 0; index < length; index++)
            System.out.print(array[index] + " ");
    }
}
