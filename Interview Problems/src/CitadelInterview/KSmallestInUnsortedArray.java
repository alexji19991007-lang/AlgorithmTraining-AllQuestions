package CitadelInterview;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

// TC using min heap: 1. Heapify all elements O(n);
//                    2. Call pop() k times to get k smallest elements O(k * log(n))
//                    O(n + k * log(n))
// TC using max heap: 1. Call insert k times O(k * log(k));
//                    2. Iterate over the remaining n - k elements one by one O((n-k) * log(k))
//                    O(k * log(k) + (n - k) * log(k)) = O(n * log(k))
// If k <<<<<< n, O(c * n) v.s. O(n * log(k)) --> hard to say
// If k ~ n (e.g. k = 0.5n), O(n * log(n)) v.s. O(n * log(n)) --> hard to say
public class KSmallestInUnsortedArray {
    public static void main(String[] args) {
       int[] arr = {2, 3, 1, 4, 5, 8, 6, 7};
        System.out.println(Arrays.toString(kSmallest(arr, 1)));
    }

    public static int[] kSmallest(int[] array, int k) {
        if (array.length == 0 || k == 0) {
            return new int[0];
        }
        Queue<Integer> maxHeap = new PriorityQueue<>(k, (t1, t2) -> {
            if (t1.equals(t2)) {
                return 0;
            }
            return t1 > t2 ? -1 : 1;
        });
        for (int i = 0; i < array.length; ++i) {
            if (i < k) {
                maxHeap.offer(array[i]);
            } else if (maxHeap.peek() > array[i]) {
                maxHeap.poll();
                maxHeap.offer(array[i]);
            }
        }
        int[] res = new int[k];
        for (int i = k - 1; i >= 0; --i) {
            res[i] = maxHeap.poll();
        }
        return res;
    }
}
