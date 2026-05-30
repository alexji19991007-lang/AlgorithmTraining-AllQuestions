public class MultiThreadedMergeSort {
    public static int[] mergeSort(int[] array) {
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

        // For top-level calls, use multithreading.
        if (right - left > 1000) {  // arbitrary threshold, can be tuned
            Thread leftSorter = new Thread(() -> mergeSortHelper(array, left, mid));
            Thread rightSorter = new Thread(() -> mergeSortHelper(array, mid + 1, right));

            leftSorter.start();
            rightSorter.start();

            try {
                leftSorter.join();
                rightSorter.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            // For smaller sub-arrays, just use the single-threaded method.
            mergeSortHelper(array, left, mid);
            mergeSortHelper(array, mid + 1, right);
        }

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

    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 1, 5, 6};
        mergeSort(arr);
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}