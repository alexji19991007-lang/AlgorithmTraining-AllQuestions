public class CanIWin2 {
    public int canWin(int[] nums) {
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
                // takeLeft: 如果我们当前选择nums[row]那一块，那还剩下来nums[row + 1 ... (row + offset)]
                int takeLeft = nums[row] +
                        (nums[row + 1] > nums[row + offset] ? dp[row + 2][row + offset] : dp[row + 1][row + offset - 1]);
                // takeRight: 如果我们当前选择nums[row + offset]那一块，那还剩下来nums[row ... (row + offset - 1)]
                int takeRight = nums[row + offset] +
                        (nums[row] > nums[row + offset - 1] ? dp[row + 1][row + offset - 1] : dp[row][row + offset - 2]);
                // 对手会在剩下来中取多的那一头
                dp[row][row + offset] = Math.max(takeLeft, takeRight);
            }
        }
        return dp[0][nums.length - 1];
    }
}
