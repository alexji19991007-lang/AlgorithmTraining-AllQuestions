public class CoinChange2 {
    public int change(int amount, int[] coins) {
        // dp[i][j] = # of combinations for coins[0 .. i - 1] to make up to amount j
        // We want to know dp[coins.length][amount];
        // dp[i][j] = dp[i][j - coins[i - 1]] + dp[i - 1][j]
        //            use (j - coins[i] >= 0)      not use
        // Base case: dp[0][0] = 1
        if (amount == 0) {
            return 1;
        }
        if (coins == null || coins.length == 0) {
            return 0;
        }
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int j = coin; j <= amount; ++j) {
                dp[j] += dp[j - coin];
            }
        }
        return dp[amount];
    }
}
