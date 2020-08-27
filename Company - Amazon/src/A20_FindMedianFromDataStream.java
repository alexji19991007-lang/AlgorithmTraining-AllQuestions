import java.util.PriorityQueue;
import java.util.Queue;

// LeetCode 295
public class A20_FindMedianFromDataStream {
    // TC: O(logn) for addNum(), O(1) for findMedium()
    // SC: O(n)
    private Queue<Long> largeHalf = new PriorityQueue<>();
    private Queue<Long> smallHalf = new PriorityQueue<>((l1, l2) -> {
        if (l1.equals(l2)) {
            return 0;
        }
        return l1 > l2 ? -1 : 1;
    });

    public void addNum(int num) {
        largeHalf.offer((long)num);
        smallHalf.offer(largeHalf.poll());
        // largeHalf.size() = smallHalf.size() or smallHalf.size() + 1;
        //                         even                  odd
        if (largeHalf.size() < smallHalf.size()) {
            largeHalf.offer(smallHalf.poll());
        }
    }

    public double findMedian() {
        return largeHalf.size() > smallHalf.size() ? largeHalf.peek() : (largeHalf.peek() + smallHalf.peek()) / 2.0;
    }
}
