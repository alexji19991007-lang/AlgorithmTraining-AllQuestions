package CitadelInterview;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class KBiggestInUnsortedArray {
    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 4, 5, 8, 6, 7};
        System.out.println(Arrays.toString(kBiggest(arr, 3)));
        System.out.println(Arrays.toString(kBiggest_2(arr, 3)));
    }

    public static int[] kBiggest(int[] array, int k) {
        if (array.length == 0 || k == 0) {
            return new int[0];
        }
        Queue<Integer> minHeap = new PriorityQueue<>(k, (t1, t2) -> {
            if (t1.equals(t2)) {
                return 0;
            }
            return t1 < t2 ? -1 : 1;
        });
        for (int i = 0; i < array.length; ++i) {
            if (i < k) {
                minHeap.offer(array[i]);
            } else if (minHeap.peek() < array[i]) {
                minHeap.poll();
                minHeap.offer(array[i]);
            }
        }
        int[] res = new int[k];
        for (int i = k - 1; i >= 0; --i) {
            res[i] = minHeap.poll();
        }
        return res;
    }

    public static int[] kBiggest_2(int[] array, int k) {
        if (array.length == 0 || k == 0) {
            return new int[0];
        }
        if (array.length <= k) {
            return array;
        }
        int left = 0, right = array.length - 1;
        int[] res = new int[k];
        while (left < right) {
            int j = partition(array, left, right);
            if (j < k) {
                left = j + 1;
            } else if (j > k) {
                right = j - 1;
            } else {
                break;
            }
        }
        System.arraycopy(array, 0, res, 0, k);
        return res;
    }

    private static int partition(int[] array, int left, int right) {
        Random rand = new Random();
        int pivot = left + rand.nextInt(right - left + 1);
        swap(array, pivot, right);
        int start = left, end = right - 1;
        while (start <= end) {
            if (array[start] > array[right]) {
                start++;
            } else {
                swap(array, start, end);
                end--;
            }
        }
        swap(array, start, right);
        return start;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
