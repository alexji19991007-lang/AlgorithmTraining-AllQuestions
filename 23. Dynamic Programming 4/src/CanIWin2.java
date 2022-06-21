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
        // size = the current game size. We already have solution for game size 1 and 2 above, so start from 3.
        for (int size = 3; size <= n; ++size) {
            for (int row = 0; row + size - 1 < n; ++row) {
                // 我们现在要从nums[leftIndex ... rightIndex]中选择
                // Left boundary of the game under consideration
                int leftIndex = row;
                // Right boundary of the game under consideration
                int rightIndex = row + size - 1;
                // takeLeft: 如果我们当前选择最左边，那还剩下来nums[(leftIndex + 1) ... rightIndex]
                int takeLeft = nums[leftIndex] + (nums[leftIndex + 1] > nums[rightIndex] ?
                        dp[leftIndex + 2][rightIndex] : dp[leftIndex + 1][rightIndex - 1]);
                // takeRight: 如果我们当前选择最右边，那还剩下来nums[leftIndex ... (rightIndex - 1)]
                int takeRight = nums[rightIndex] + (nums[leftIndex] > nums[rightIndex - 1] ?
                        dp[leftIndex + 1][rightIndex - 1] : dp[leftIndex][rightIndex - 2]);
                // 注意：对手会在我们取完后从剩下的两头中选取较大的那个数字，所以我们要考虑在去掉较大数字后，小一号game的最终结果 + 当前选择 = 当前game的结果
                dp[leftIndex][rightIndex] = Math.max(takeLeft, takeRight);
            }
        }
        return dp[0][n - 1];
    }
}
