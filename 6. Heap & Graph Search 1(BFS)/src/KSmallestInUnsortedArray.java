import java.util.*;

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
