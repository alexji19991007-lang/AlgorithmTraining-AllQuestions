public class CoinChange {
    public static void main(String[] args) {
        int[] coins = {2, 3, 5};
        int amount = 11;
        System.out.println(coinChange(coins, amount));
    }

    public static int coinChange(int[] coins, int amount) {
        // dp[i] means the least amount of coins required to reach the amount i.
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                // if we decide to use one piece of one particular coin, then what's the minimum
                // number of coins needed to reach the remaining amount (this can be read from table)?

                // we will only do this check when the value of the current coin is smaller than
                // the amount i and the amount i - coin can be reached by other coins.
                if (i - coin >= 0 && dp[i - coin] != -1)
                    min = Math.min(dp[i - coin], min);
            }
            // Set dp[i] to -1 if i (current amount) can not be reach by coins array
            dp[i] = min == Integer.MAX_VALUE ? -1 : 1 + min;
        }
        return dp[amount];
    }
}
