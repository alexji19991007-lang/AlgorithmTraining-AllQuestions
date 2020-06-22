package BestBuyAndSellStock;

public class BestBuyAndSell4 {
    /**
     * dp[i, j] represents the max profit up until prices[j] using at most i transactions.
     * dp[i, j] = max(dp[i, j-1], prices[j] - prices[x] + dp[i-1, x]) { x in range of [0, j-1] }
     *          = max(dp[i, j-1], prices[j] + max(dp[i-1, x] - prices[x]))
     * dp[0, j] = 0; 0 transactions makes 0 profit
     * dp[i, 0] = 0; if there is only one price data point you can't make any transaction.
     */
    public int maxProfit(int[] array, int k) {
        if (k >= array.length / 2) {
            return maxProfit(array);
        }
        int len = array.length;
        int[][] dp = new int[k + 1][len];
        for (int i = 1; i <= k; i++) {
            int localMax = dp[i - 1][0] - array[0];
            for (int j = 1; j < len; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], array[j] + localMax);
                localMax = Math.max(localMax, dp[i - 1][j - 1] - array[j]);
            }
        }
        return dp[k][len - 1];
    }

    // Max profit if we can make as many transactions as possible (at most prices.length / 2 times)
    public int maxProfit(int[] prices) {
        // 计算每一笔小交易
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                maxprofit += prices[i] - prices[i - 1];
            }
        }
        return maxprofit;
    }
}
