import java.util.HashMap;

// LeetCode 1027
public class LongestArithmeticSequence {
    public int longestArithSeqLength(int[] A) {
        int res = 2, n = A.length;
        // dp[i].get[d] = the length of the arithmetic sequence ending at index i with a common
        //                difference d
        HashMap<Integer, Integer>[] dp = new HashMap[n];
        for (int i = 0; i < A.length; ++i) {
            dp[i] = new HashMap<>();
            for (int j = 0; j < i; ++j) {
                int d = A[i] - A[j];
                dp[i].put(d, dp[j].getOrDefault(d, 1) + 1);
                res = Math.max(res, dp[i].get(d));
            }
        }
        return res;
    }
}
