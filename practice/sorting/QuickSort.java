package practice.sorting;

public class QuickSort {

    static int partition(int start, int end, int[] array) {
        int pivot = array[end];
        int finalIndexOfPivot = start; // say

        // iterate over the array and find the finalIndexOfPivot such that all elements to the
        // left of the array are smaller than the pivot

        for (int i = start; i <= end - 1; i++) {
            if (array[i] >= pivot) {
                // swap the current element with next element
                int temp = array[i];
                array[i] = array[finalIndexOfPivot];
                array[finalIndexOfPivot] = temp;
                // move finalIndexOfPivot 1 step ahead
                finalIndexOfPivot ++;
            }
        }
        // move pivot to its final index
        int temp = array[finalIndexOfPivot];
        array[finalIndexOfPivot] = array[end];
        array[end] = temp;

        return finalIndexOfPivot;
    }

    static void quickSortArray(int start, int end, int[] array) {
        if (start < end) {
            
            // partionIndex => all elements to the left of the partition index 
            // will be less than the pivot, and
            // all elements right of the partition index will be more than the pivot chose in
            // the partition function
            int partionIndex = partition(start, end, array);
            quickSortArray(start, partionIndex - 1, array);
            quickSortArray(partionIndex + 1, end, array);
        }
    }

    public static void main(String args[]) {
        int[] arr = { 6, 8, 9, 4, 1, 5 };
        int length = 6;
        quickSortArray(0, length - 1, arr);

        for (int index = 0; index < length; index++)
            System.out.print(arr[index] + " ");
    }
}
