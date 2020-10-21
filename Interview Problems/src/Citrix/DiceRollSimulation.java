package Citrix;

// LeetCode 1347
// TC: O(n * 6)
// SC: O(n * 6)
public class DiceRollSimulation {
    public int dieSimulator(int n, int[] rollMax) {
        int mod = (int)1e9 + 7;
        // dp[i][j] represents the number of distinct sequences that can be obtained when rolling i times and ending with j
        // The one more row represents the total sequences when rolling i times
        int[][] dp = new int[n + 1][7];
        // init for the first roll
        for (int i = 0; i < 6; i++) {
            dp[1][i] = 1;
        }
        dp[1][6] = 6;
        for (int i = 2; i <= n; i++) {
            int total = 0;
            for (int j = 0; j < 6; j++) {
                // If there are no constraints, the total sequences ending with j should be the total sequences from previous rolling
                dp[i][j] = dp[i - 1][6];
                // For xx1, only 111 is not allowed, so we only need to remove 1 sequence from previous sum
                if (i - rollMax[j] == 1) {
                    dp[i][j]--;
                }
                // For axx1, we need to remove the number of a11 (211 + 311 + 411 + 511 + 611) => (..2 + ..3 + ..4 + ..5 + ..6) => (sum - ..1)
                if (i - rollMax[j] >= 2) {
                    int reduction = dp[i - rollMax[j] - 1][6] - dp[i - rollMax[j] - 1][j];
                    // must add one more mod because subtraction may introduce negative numbers
                    dp[i][j] = ((dp[i][j] - reduction) % mod + mod) % mod;
                }
                total = (total + dp[i][j]) % mod;
            }
            dp[i][6] = total;
        }
        return dp[n][6];
    }
}
