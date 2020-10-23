import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    public static void main(String[] args) {
        int[] array = {5, 6, 1, 3, 4, 2, 8, 7};
        System.out.println(Arrays.toString(quickSort(array)));
    }

    public static int[] quickSort(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        quickSortHelper(array, 0, array.length - 1);
        return array;
    }

    public static void quickSortHelper(int[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);
            quickSortHelper(arr, begin, partitionIndex - 1);
            quickSortHelper(arr, partitionIndex + 1, end);
        }
    }

    public static int partition(int[] array, int begin, int end) {
        int pivot = begin + (int) (Math.random() * (end - begin + 1));
        swap(array, pivot, end);
        int i = begin;
        int j = end - 1;
        while (i <= j) {
            if (array[i] < array[end]) {
                i++;
            } else if (array[j] >= array[end]) {
                j--;
            } else {
                swap(array, i++, j--);
            }
        }
        swap(array, i, end);
        return i;
    }

    public static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
