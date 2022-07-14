package HackerRank;

public class WaysToSum {
    public static void main(String[] args) {
        WaysToSum test = new WaysToSum();
        System.out.println(test.numWays(8, 2));
    }

    public int numWays(int total, int k) {
        // dp[i][j] = the number of ways to reach sum j using numbers in range [1, i];
        int[][] dp = new int[k + 1][total + 1];
        for (int i = 1; i <= k; ++i) {
            for (int j = 0; j <= total; ++j) {
                if (j == 0) {
                    // dp[i][0] = 0 because there is only one way to reach a sum of 0
                    dp[i][j] = 1;
                } else if (j >= i) {
                    // if the sum we want to reach is greater than the largest number
                    // dp[i - 1][j]: if we don't use the current largest number, what's the total number of ways to reach sum j
                    // dp[i][j - i]: if we just use one of the current largest number, then the total sum becomes j - i, so we need to know how many ways we can reach sum j - i;
                    dp[i][j] = dp[i - 1][j] + dp[i][j - i];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[k][total];
    }
}
