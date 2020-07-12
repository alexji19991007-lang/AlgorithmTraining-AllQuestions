import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MedianTracker {
    private Queue<Integer> largerHalf;
    private Queue<Integer> smallerHalf;
    private int size;

    public MedianTracker() {
        largerHalf = new PriorityQueue<>(); // This is a min heap
        smallerHalf = new PriorityQueue<>((i1, i2) -> {
            if (i1.equals(i2)) return 0;
            return i1 > i2 ? -1 : 1;
        }); // This is a max heap
        size = 0;
    }

    public void read(int value) {
        // Maintain the following properties:
        // 1. Even #: size(smallerHalf) == size(largerHalf)
        // 2.  Odd #: size(smallerHalf) == size(largerHalf) + 1
        if (smallerHalf.isEmpty() || value <= smallerHalf.peek()) {
            smallerHalf.offer(value);
        } else {
            largerHalf.offer(value);
        }
        // After we insert the value, only when size(smallerHalf) == size(largerHalf) + 2
        // or size(smallerHalf) == size(largerHalf) - 1, will break the balance, and we
        // need to do the adjustment accordingly
        if (smallerHalf.size() > largerHalf.size() + 1) {
            largerHalf.offer(smallerHalf.poll());
        } else if (smallerHalf.size() < largerHalf.size()) {
            smallerHalf.offer(largerHalf.poll());
        }
        size++;
    }

    public Double median() {
        if (size <= 0) {
            return null;
        }
        return size % 2 == 0 ? (smallerHalf.peek() + largerHalf.peek()) / 2.0 : (double)(smallerHalf.peek());
    }
}
