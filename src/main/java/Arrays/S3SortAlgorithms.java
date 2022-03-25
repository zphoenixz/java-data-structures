package Arrays;

import java.util.Arrays;

public class S3SortAlgorithms {

    // Unstable sort, items with same value do not preserve same order when sorted
    // Stable sort, items with same value do preserve same order when sorted
    // 9 < 9 ... no! then don't swap in order to get an stable sort
    public static void main(String[] args) {
        // O(N^2)
//        bubbleSort();

        // O(N^2) - unstable
//        selectionSort();

        // O(N^2) - stable
//        insertionSort();

        // O(N^2) but Depends on the choosing gap - unstable
//        shellSort();

        //O(nlogn) - stable
//        mergeSort();

        //O(nlogn) - unstable
//        quickSort();

        //O(n) by making assupmtions about the data to be sorted - stable
//        countingSort();

        //O(n) by making assupmtions about the data to be sorted - stable
        radixSort();
        // DualPivotQuickSort O(nlogn)
        int[] intArray = { 20, 35, -15, 7, 55, 1, -22 };
        Arrays.sort(intArray);

        // Parallel sort merge (Hybrid alg), uses threads
        Arrays.parallelSort(intArray);
    }

    private static void bubbleSort() {
        int[] intArray = { 20, 35, -15, 7, 55, 1, -22 };
        for (int lastUnsortedIndex = intArray.length - 1; lastUnsortedIndex > 0;
             lastUnsortedIndex--) {
            for (int i = 0; i < lastUnsortedIndex; i++) {
                if (intArray[i] > intArray[i + 1]) {
                    swap(intArray, i, i + 1);
                }
            }
        }
        for (int i = 0; i < intArray.length; i++) {
            System.out.println(intArray[i]);
        }
    }

    private static void selectionSort() {
        int[] intArray = { 20, 35, -15, 7, 55, 1, -22 };
        for (int lastUnsortedIndex = intArray.length - 1; lastUnsortedIndex > 0;
             lastUnsortedIndex--) {
            int largest = 0;
            for (int i = 1; i <= lastUnsortedIndex; i++) {
                if (intArray[i] > intArray[largest]) {
                    largest = i;
                }
            }
            swap(intArray, largest, lastUnsortedIndex);
        }
        for (int i = 0; i < intArray.length; i++) {
            System.out.println(intArray[i]);
        }

    }

    private static void insertionSort(){
        int[] intArray = { 20, 35, -15, 7, 55, 1, -22 };

        for (int firstUnsortedIndex = 1; firstUnsortedIndex < intArray.length;
             firstUnsortedIndex++) {
            int newElement = intArray[firstUnsortedIndex];
            int i;
            for (i = firstUnsortedIndex; i > 0 && intArray[i - 1] > newElement; i--) {
                intArray[i] = intArray[i - 1];
            }
            intArray[i] = newElement;
        }

        for (int i = 0; i < intArray.length; i++) {
            System.out.println(intArray[i]);
        }
    }

    private static void shellSort(){
        int[] intArray = { 20, 35, -15, 7, 55, 1, -22 };
        for (int gap = intArray.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < intArray.length; i++) {
                int newElement = intArray[i];
                int j = i;
                while (j >= gap && intArray[j - gap] > newElement) {
                    intArray[j] = intArray[j - gap];
                    j -= gap;
                }

                intArray[j] = newElement;
            }
        }

        for (int i = 0; i < intArray.length; i++) {
            System.out.println(intArray[i]);
        }
    }

    private static void mergeSort(){
        int[] intArray = { 20, 35, -15, 7, 55, 1, -22 };

        mergeSortExec(intArray, 0, intArray.length);

        for (int i = 0; i < intArray.length; i++) {
            System.out.println(intArray[i]);
        }
    }
    // { 20, 35, -15, 7, 55, 1, -22 }
    private static void mergeSortExec(int[] input, int start, int end) {
        if (end - start < 2) {
            return;
        }
        int mid = (start + end) / 2;
        mergeSortExec(input, start, mid);
        mergeSortExec(input, mid, end);
        merge(input, start, mid, end);
    }

    // { 20, 35, -15, 7, 55, 1, -22 }
    private static void merge(int[] input, int start, int mid, int end) {
        if (input[mid - 1] <= input[mid]) {
            return;
        }
        int i = start;
        int j = mid;
        int tempIndex = 0;
        int[] temp = new int[end - start];
        while (i < mid && j < end) {
            temp[tempIndex++] = input[i] <= input[j] ? input[i++] : input[j++];
        }
        System.arraycopy(input, i, input, start + tempIndex, mid - i);
        System.arraycopy(temp, 0, input, start, tempIndex);
    }

    private static void swap(int[] array, int i, int j) {
        if (i == j) {
            return;
        }
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static void quickSort(){
        int[] intArray = { 20, 35, -15, 7, 55, 1, -22 };

        quickSortExec(intArray, 0, intArray.length);

        for (int i = 0; i < intArray.length; i++) {
            System.out.println(intArray[i]);
        }
    }

    private static void quickSortExec(int[] input, int start, int end) {
        if (end - start < 2) {
            return;
        }
        int pivotIndex = partition(input, start, end);
        quickSortExec(input, start, pivotIndex);
        for (int i = 0; i < input.length; i++) {
            System.out.println(input[i]);
        }
        quickSortExec(input, pivotIndex + 1, end);
    }

    private static int partition(int[] input, int start, int end) {
        // This is using the first element as the pivot
        int pivot = input[start];
        int i = start;
        int j = end;
        while (i < j) {

            // NOTE: empty loop body
            while (i < j && input[--j] >= pivot);
            if (i < j) {
                input[i] = input[j];
            }

            // NOTE: empty loop body
            while (i < j && input[++i] <= pivot);
            if (i < j) {
                input[j] = input[i];
            }
        }
        input[j] = pivot;
        return j;
    }

    private static void countingSort(){
        int[] intArray = { 2, 5, 9, 8, 2, 8, 7, 10, 4, 3 };
        countingSortExec(intArray, 1, 10);
        for (int i = 0; i < intArray.length; i++) {
            System.out.println(intArray[i]);
        }
    }

    private static void countingSortExec(int[] input, int min, int max) {
        int[] countArray = new int[(max - min) + 1];
        for (int i = 0; i < input.length; i++) {
            countArray[input[i] - min]++;
        }
        int j = 0;
        for (int i = min; i <= max; i++) {
            while (countArray[i - min] > 0) {
                input[j++] = i;
                countArray[i - min]--;
            }
        }
    }

    private static void radixSort(){
        int[] radixArray = { 4725, 4586, 1330, 8792, 1594, 5729 };
        radixSortExec(radixArray, 10, 4);
        for (int i = 0; i < radixArray.length; i++) {
            System.out.println(radixArray[i]);
        }
    }

    public static void radixSortExec(int[] input, int radix, int width) {
        for (int i = 0; i < width; i++) {
            radixSingleSort(input, i, radix);
        }
    }

    public static void radixSingleSort(int[] input, int position, int radix) {
        int numItems = input.length;
        int[] countArray = new int[radix];
        for (int value: input) {
            countArray[getDigit(position, value, radix)]++;
        }
        // Adjust the count array
        for (int j = 1; j < radix; j++) {
            countArray[j] += countArray[j - 1];
        }
        int[] temp = new int[numItems];
        for (int tempIndex = numItems - 1; tempIndex >= 0; tempIndex--) {
            temp[--countArray[getDigit(position, input[tempIndex], radix)]] =
                    input[tempIndex];
        }
        for (int tempIndex = 0; tempIndex < numItems; tempIndex++) {
            input[tempIndex] = temp[tempIndex];
        }
    }

    public static int getDigit(int position, int value, int radix) {
        return value / (int) Math.pow(radix, position) % radix;
    }
}
