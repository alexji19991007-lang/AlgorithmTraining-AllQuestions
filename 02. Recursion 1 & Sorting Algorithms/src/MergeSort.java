import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] array = {5, 6, 1, 3, 4, 2, 8, 7};
        System.out.println(Arrays.toString(mergeSort(array)));
    }

    public static int[] mergeSort(int[] array) {
        // Write your solution here
        if (array == null || array.length <= 1) {
            return array;
        }
        mergeSortHelper(array, 0, array.length - 1);
        return array;
    }

    public static void mergeSortHelper(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSortHelper(array, left, mid);
        mergeSortHelper(array, mid + 1, right);
        merge(array, left, mid, mid + 1, right);
    }

    public static void merge(int[] array, int oneStart, int oneEnd, int twoStart, int twoEnd) {
        if (oneStart == twoStart) {
            return;
        }
        int finalStart = oneStart;
        int[] buffer = new int[twoEnd - oneStart + 1];
        int cur = 0;
        while (oneStart <= oneEnd && twoStart <= twoEnd) {
            buffer[cur++] = array[oneStart] < array[twoStart] ? array[oneStart++] : array[twoStart++];
        }
        while (oneStart <= oneEnd) {
            buffer[cur++] = array[oneStart++];
        }
        while (twoStart <= twoEnd) {
            buffer[cur++] = array[twoStart++];
        }
        cur = 0;
        while (finalStart <= twoEnd) {
            array[finalStart++] = buffer[cur++];
        }
    }
}
