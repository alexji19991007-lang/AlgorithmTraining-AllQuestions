import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class KthSmallestWith23AsFactors {
    public static void main(String[] args) {
        KthSmallestWith23AsFactors test = new KthSmallestWith23AsFactors();
        System.out.println(test.kth(40));
    }

    public int kth(int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);
        Set<Integer> visited = new HashSet<>();
        minHeap.offer(1);
        visited.add(1);
        while (k > 1) {
            int current = minHeap.poll();
            if (visited.add(2 * current)) {
                minHeap.offer(2 * current);
            }
            if (visited.add(3 * current)) {
                minHeap.offer(3 * current);
            }
            k--;
        }
        return minHeap.peek();
    }
}
