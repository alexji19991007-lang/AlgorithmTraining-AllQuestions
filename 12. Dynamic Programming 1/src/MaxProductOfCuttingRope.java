public class MaxProductOfCuttingRope {
    // 1. What does dp[i] mean?
    //      dp[i] = The maximum product we can get by cutting a rope of length i
    //      (We are allowed to either keep a segment as is, or further cut it)
    // 2. What is the base case?
    //      dp[1] = 1
    //      Because when the rope length is 1, the maximum product we can get is 1
    //      (even though we cannot make a valid cut, this is used to help build larger results)
    // 3. What is the recurrence relations?
    //      When we try to compute dp[i], we try every possible first cut position j (1 <= j < i)
    //      We cut the rope into two parts:
    //          left part: length j
    //          right part: length (i - j)
    //
    // For the left part (length j), we have two choices:
    //    3.1 Do not cut it → product = j
    //    3.2 Cut it further → product = dp[j]
    //
    // So the best for left part is: max(j, dp[j])
    //
    // For the right part (length i - j), we DO NOT look up dp[i - j]
    // because we are enumerating all possibilities from left side already
    // (this avoids duplicate counting)
    //
    // So for each j:
    //
    // candidate = max(j, dp[j]) * (i - j)
    //
    // We take the maximum over all j:
    //
    // dp[i] = max over all j in (1, i):
    //         max(j, dp[j]) * (i - j)

    // Make at least 1 cut
    // TC: O(n^2)
    // SC: O(n)
    public int maxProduct(int length) {
        int[] M = new int[length + 1];
        M[1] = 1;
        for (int i = 2; i < length + 1; ++i) {
            for (int j = 1; j < i; ++j) {
                // 左大段 --> 怎么切？ --> 查
                // 右小段 --> 不切 直接乘
                M[i] = Math.max(M[i], Math.max(j, M[j]) * (i - j));
            }
        }
        return M[length];
    }

    // Make at least k cuts
    // TC: O(kn^2)
    // SC: O(nk) --> improve to O(n) since we only need two rows to store the solution
    public int curRope2(int n, int k) {
        if (n < 1) return 0;
        if (k >= n) return 0;
        // Assume n >= 1 & k < n
        // dp[i][j] means the maximum product of cutting a rope of length j, with at least i cuts.
        int[][] dp = new int[2][n + 1];
        dp[0][1] = 1;
        for (int i = 0; i < k; ++i) { // for each row (0 ... k)
            for (int j = i + 1; j <= n; ++j) { // for each column (1 ... n)
                if (i == 0) { // 可切可不切
                    // This is the same as cut rope 1
                    for (int cut = 1; cut < j; ++cut) {
                        dp[0][j] = Math.max(dp[0][j], dp[0][cut] * (j - cut));
                    }
                    // Compare with the case where no cut is made
                    dp[i % 2][j] = Math.max(j, dp[i % 2][j]);
                } else { // 必须切一刀
                    // <------>/<-------> (total length j, already one cut made)
                    //   cut     j - cut
                    for (int cut = 1; cut < j; ++cut) {
                        dp[i % 2][j] = Math.max(dp[i % 2][j], dp[(i - 1) % 2][cut] * (j - cut));
                        //                                    -1 here because already one cut has been made
                    }
                }
            }
        }
        return dp[k % 2][n];
    }
}
