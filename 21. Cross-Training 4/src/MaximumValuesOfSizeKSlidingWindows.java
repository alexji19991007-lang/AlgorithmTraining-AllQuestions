import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class MaximumValuesOfSizeKSlidingWindows {
    public List<Integer> maxWindows(int[] array, int k) {
        List<Integer> max = new ArrayList<>();
        // Deque stores the index of all elements, and we make sure that:
        // 1. The deque only contains indices of elements in the current sliding window.
        // 2. For any index, the previous index with smaller value is discarded from the deque.
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < array.length; ++i) {
            // discard any index with a smaller value than index i;
            // 如果当前进来的数字比在他之前的数字大，那之前的所有数字不可能成为当前以及接下来任何sliding window的
            // max value
            while (!deque.isEmpty() && array[deque.peekLast()] <= array[i]) {
                deque.pollLast();
            }
            // It is possible that the head is out of our current sliding window, so
            // we need to discard it as well
            if (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            deque.offerLast(i);
            if (i >= k - 1) {
                max.add(array[deque.peekFirst()]);
            }
        }
        return max;
    }
}
