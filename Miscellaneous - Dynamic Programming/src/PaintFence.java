public class PaintFence {
    public int numWays(int n, int k) {
        if (n == 0) return 0;
        if (n == 1) return k;
        if (n == 2) return k * k;
        int[] dp = new int[3];
        dp[0] = k;
        dp[1] = k * k;
        for (int i = 2; i < n; ++i) {
            //        different color with i - 1  or same color with i - 1 (different color with i - 2)
            dp[i % 3] = (k - 1) * dp[(i - 1) % 3] + (k - 1) * dp[(i - 2) % 3];
        }
        return dp[(n - 1) % 3];
    }
}
