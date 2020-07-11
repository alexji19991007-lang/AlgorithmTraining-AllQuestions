package PaintHouse;

public class PaintHouse {
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;
        int r = costs.length;
        // dp[i][j] = min cost of paint house 0 ... i, with house i's color as j
        int[][] dp = new int[r][3];
        // Base case
        for (int c = 0; c <= 2; ++c) {
            dp[0][c] = costs[0][c];
        }
        // The following can be improved to use O(1) space
        for (int i = 1; i < r; ++i) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][1];
            dp[i][2] = Math.min(dp[i - 1][1], dp[i - 1][0]) + costs[i][2];
        }
        return Math.min(Math.min(dp[r - 1][0], dp[r - 1][1]), dp[r - 1][2]);
    }
}
