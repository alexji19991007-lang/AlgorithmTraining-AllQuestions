import java.util.Arrays;
import java.util.PriorityQueue;

public class KthSmallestInUnsortedArray {
    public static void main(String[] args) {
        int[] array = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(kSmallest(array, 3)));
    }

    public static int[] kSmallest(int[] array, int k) {
        if (array.length == 0 || k == 0) {
            return new int[0];
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, (t1, t2) -> {
            if (t1.equals(t2)) {
                return 0;
            }
            return t1 > t2 ? -1 : 1;
        });
        for (int i = 0; i < array.length; ++i) {
            if (i < k) {
                maxHeap.offer(array[i]);
            } else if (array[i] < maxHeap.peek()) {
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
