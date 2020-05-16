import java.util.ArrayDeque;
import java.util.Deque;

public class ShortestSubarrayWithSumAtLeastK {
    public static void main(String[] args) {
        int[] A = {2,-1,2,-2,2,2,1};
        System.out.println(shortestSubarray(A, 4));
    }


    public static int shortestSubarray(int[] A, int K) {
        int n = A.length;
        int[] B = new int[n + 1];
        // Calculate the prefix sum
        // Sum from A[i] to A[j] (tail-inclusive) is calculated by B[j + 1] - B[i]
        // j + 1 - i represents the length of the subarray from A[i] to A[j]
        for (int i = 0; i < n; ++i) {
            B[i + 1] = B[i] + A[i];
        }
        // the deque is used to hold indices
        Deque<Integer> dq = new ArrayDeque<>();
        int res = Integer.MAX_VALUE;
        //    0  1   2   3  4  5  6   7
        // A: 2  -1  2  -2  2  2  1
        // B: 0  2   1   3  1  3  5   6
        for (int i = 0; i < n + 1; ++i) {
            // If x1 < x2 and B[x2] < B[x1], then x1 can never be the start of the solution subarray
            // because if the subarray from x1 to i is the shortest subarray with sum at least k, then
            // since B[x2] < B[x1], the subarry from x2 to i will also have a sum at least k, and with
            // a smaller length!! Thus, we eliminate x1 as a candidate.
            while (dq.size() > 0 && B[i] < B[dq.getLast()]) {
                dq.pollLast();
            }
            // get a possible solution, then work toward the smallest subarray
            while (dq.size() > 0 && B[i] - B[dq.getFirst()] >= K) {
                res = Math.min(res, i - dq.pollFirst());
            }
            dq.offerLast(i);
        }
        return res <= n ? res : -1;
    }
}
