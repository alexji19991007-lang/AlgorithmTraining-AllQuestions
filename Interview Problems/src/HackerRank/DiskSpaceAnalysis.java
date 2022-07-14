package HackerRank;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class DiskSpaceAnalysis {
    public static void main(String[] args) {
        DiskSpaceAnalysis test = new DiskSpaceAnalysis();
        System.out.println(test.segment(2, new int[]{8, 2, 4, 6}));
    }

    public int segment(int x, int[] space) {
        if (x == 1) {
            return space[0];
        }
        //int n = space.length;
        //int[] res = new int[n - x + 1];
        int maximumOfMins = Integer.MIN_VALUE;
        //int idx = 0;
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < space.length; ++i) {
            while (!dq.isEmpty() && dq.peekFirst() < i - x + 1) {
                dq.pollFirst();
            }
            while (!dq.isEmpty() && space[dq.peekLast()] > space[i]) {
                dq.pollLast();
            }
            dq.offerLast(i);
            if (i >= x - 1) {
                //res[idx++] = space[dq.peekFirst()];
                maximumOfMins = Math.max(maximumOfMins, space[dq.peekFirst()]);
            }
        }
        return maximumOfMins;
    }
}
