public class MergeStones {
    public int minCost(int[] stones) {
        if (stones.length == 1) {
            return 0;
        }
        int[] prefixSum = new int[stones.length];
        prefixSum[0] = stones[0];
        for (int i = 1; i < stones.length; ++i) {
            prefixSum[i] = prefixSum[i - 1] + stones[i];
        }
        int[][] dp = new int[stones.length][stones.length];
        for (int i = 0; i < stones.length; ++i) {
            dp[i][i] = 0;
        }
        for (int offset = 1; offset < stones.length; ++offset) {
            for (int i = 0; i < stones.length - offset; ++i) {
                // i = curRow, j = curCol
                int j = i + offset;
                int costLastStep = i == 0 ? prefixSum[j] : prefixSum[j] - prefixSum[i - 1];
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; ++k) {
                    dp[i][j] = Math.min(dp[i][j], costLastStep + dp[i][k] + dp[k + 1][j]);
                }
            }
        }
        return dp[0][stones.length - 1];
    }
}
