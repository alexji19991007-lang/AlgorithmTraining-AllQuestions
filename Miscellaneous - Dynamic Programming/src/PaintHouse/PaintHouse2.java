package PaintHouse;

public class PaintHouse2 {
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;
        int n = costs.length;
        int k = costs[0].length;
        // dp[i][j] = min cost of paint house 0 ... i, with house i's color as j
        int[][] dp = new int[2][k];
        // Base case
        for (int c = 0; c < k; ++c) {
            dp[0][c] = costs[0][c];
        }
        for (int i = 1; i < n; ++i) {
            int[] minCosts = findMinCosts(dp[(i - 1) % 2]);
            int minCost = minCosts[0];
            int minCostColor = minCosts[1];
            int secondMinCost = minCosts[2];
            for (int c = 0; c < k; ++c) {
                dp[i % 2][c] = costs[i][c];
                dp[i % 2][c] += (c == minCostColor) ? secondMinCost : minCost;
            }
        }
        int res = dp[(n - 1) % 2][0];
        for (int c = 0; c < k; ++c) {
            res = Math.min(dp[(n - 1) % 2][c], res);
        }
        return res;
    }

    private int[] findMinCosts(int[] array) {
        int minCost = Integer.MAX_VALUE;
        int minCostColor = -1;
        int secondMinCost = Integer.MAX_VALUE;
        for (int c = 0; c < array.length; ++c) {
            if (array[c] < minCost) {
                if (minCost < secondMinCost) {
                    secondMinCost = minCost;
                }
                minCost = array[c];
                minCostColor = c;
            } else if (array[c] < secondMinCost) {
                secondMinCost = array[c];
            }
        }
        return new int[]{minCost, minCostColor, secondMinCost};
    }
}
