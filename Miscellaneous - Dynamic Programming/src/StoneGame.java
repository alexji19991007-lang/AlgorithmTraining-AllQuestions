public class StoneGame {
    public static void main(String[] args) {
        int[] nums = {2, 1, 100, 3};
        System.out.println(stoneGame2(nums));
    }

    public static int stoneGame2(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        // Base case 1: 只有一块
        for (int i = 0; i < n; ++i) {
            dp[i][i] = nums[i];
        }
        // Base case 2: 有两块，从中选大的
        for (int i = 0; i < n - 1; ++i) {
            dp[i][i + 1] = Math.max(nums[i], nums[i + 1]);
        }
        for (int offset = 2; offset < n; ++offset) {
            for (int row = 0; row < n - offset; ++row) {
                // 我们现在要从nums[row ... (row + offset)]中选择
                // takeLeft: 如果我们当前选择nums[row]那一块，那还剩下来nums[row ... (row + offset)]
                int takeLeft = nums[row] + (nums[row + 1] > nums[row + offset] ? dp[row + 2][row + offset] : dp[row + 1][row + offset - 1]);
                // takeRight: 如果我们当前选择nums[row + offset]那一块，那还剩下来nums[row ... (row + offset - 1)]
                int takeRight = nums[row + offset] + (nums[row] > nums[row + offset - 1] ? dp[row + 1][row + offset - 1] : dp[row][row + offset - 2]);
                // 对手会在剩下来中取多的那一头
                dp[row][row + offset] = Math.max(takeLeft, takeRight);
            }
        }
        return dp[0][nums.length - 1];
    }

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
                dp[i][i + j] = Math.max(piles[i] - dp[i + 1][i + j], piles[i + j] - dp[i][i + j - 1]);
            }
        }
        return dp[0][n - 1] > 0;
    }
}
