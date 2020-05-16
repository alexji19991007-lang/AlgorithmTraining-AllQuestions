import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || k <= 0) {
            return new int[0];
        }
        if (k == 1) {
            return nums;
        }
        int n = nums.length;
        int[] res = new int[n - k + 1];
        int resIndex = 0;
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < nums.length; ++i) {
            // remove elements that are out of our current sliding window
            while (!dq.isEmpty() && dq.peek() < i - k + 1) {
                dq.poll();
            }
            // remove elements that are smaller than the current element until we meet an element
            // that is larger than the current one
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
                dq.pollLast();
            }
            dq.addLast(i);
            if (i >= k - 1) {
                // The element at the start of the deque should be the largest element in our current window
                res[resIndex] = nums[dq.peek()];
                resIndex++;
            }
        }
        return res;
    }
}
