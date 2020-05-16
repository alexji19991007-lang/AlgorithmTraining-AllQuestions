public class StoneGame {
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        // dp[i][j] = the biggest number of stones you can get more than the opponent by picking
        // from piles[i] to piles[j]
        // 假设对于piles[i]到piles[j]:
        // Case 1 choose piles[i]:
        //          dp[i][j] = piles[i] - dp[i + 1][j]
        //          dp[i + 1][j] = 对方先选，从piles[i + 1]到piles[j]中选，最多可以赢我多少（游戏反过来进行）
        // Case 2 choose piles[j]:
        //          dp[i][j] = piles[j] - dp[i][j - 1]
        //          dp[i][j - 1] = 对方先选，从piles[i]到piles[j - 1]中选，最多可以赢我多少（游戏反过来进行）
        // dp[i][j] = max(Case 1 & Case 2) = max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1])
        int[][] dp = new int[n][n];
        // What if only one pile exists: 斜着填表，先解决对角线
        // 因为填这个2*2的表格，除了对角线以外的值，我们都需要它正下方和正左方的值来确定当前值
        for (int i = 0; i < n; ++i) {
            dp[i][i] = piles[i];
        }
        for (int j = 1; j < n; ++j) {
            for (int i = 0; i < n - j; ++i) {
                dp[i][i + j] = Math.max(piles[i] - dp[i + 1][i + j], piles[i + j] - dp[i][i + j -1]);
            }
        }
        return dp[0][n - 1] > 0;
    }
}
