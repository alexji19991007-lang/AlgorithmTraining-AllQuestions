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
        int idx = 0;
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < nums.length; ++i) {
            while (!dq.isEmpty() && dq.peekFirst() < i - k + 1) {
                dq.pollFirst();
            }
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
                dq.pollLast();
            }
            dq.offerLast(i);
            if (i >= k - 1) {
                res[idx++] = nums[dq.peekFirst()];
            }
        }
        return res;
    }
}
