package practice.sorting;

public class MergeSort {

    static int[] merge(int[] firstSortedArray, int[] secondSortedArray, int firstArrayLength, int secondArrayLength) {
        int[] mergedArray = new int[firstArrayLength + secondArrayLength];
        int mergedArrayIndex = 0;
        int firstArrayIndex = 0;
        int secondArrayIndex = 0;

        while (firstArrayIndex < firstArrayLength && secondArrayIndex < secondArrayLength) {
            if (firstSortedArray[firstArrayIndex] < secondSortedArray[secondArrayIndex]) {
                mergedArray[mergedArrayIndex] = firstSortedArray[firstArrayIndex];
                firstArrayIndex++;
            } else {
                mergedArray[mergedArrayIndex] = secondSortedArray[secondArrayIndex];
                secondArrayIndex++;
            }
            mergedArrayIndex++;
        }

        // handle left-over items
        while (firstArrayIndex < firstArrayLength) {
            mergedArray[mergedArrayIndex] = firstSortedArray[firstArrayIndex];
            firstArrayIndex++;
            mergedArrayIndex++;
        }

        while (secondArrayIndex < secondArrayLength) {
            mergedArray[mergedArrayIndex] = secondSortedArray[secondArrayIndex];
            secondArrayIndex++;
            mergedArrayIndex++;
        }

        return mergedArray;
    }

    static int[] mergeSortArray(int array[], int length) {
        if (length < 2) {
            return array;
        }

        int mid = length / 2;
        int[] leftArray = new int[mid];
        int[] rightArray = new int[length - mid];

        // fill left array
        for (int index = 0; index <= mid - 1; index++) {
            leftArray[index] = array[index];
        }

        // fill right array
        for (int index = 0; index <= length - mid - 1; index++) {
            rightArray[index] = array[mid + index];
        }
        int[] sortedLeftArray = mergeSortArray(leftArray, mid);
        int[] sortedRightArray = mergeSortArray(rightArray, length - mid);
        return merge(sortedLeftArray, sortedRightArray, mid, length - mid);
    }

    public static void main(String args[]) {
        int[] array = { 6, 8, 9, 4, 1, 5 };
        int length = 6;
        int[] sortedArray = mergeSortArray(array, length);

        for (int index = 0; index < length; index++)
            System.out.print(sortedArray[index] + " ");
    }

}
