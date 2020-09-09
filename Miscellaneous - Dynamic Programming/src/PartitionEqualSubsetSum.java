import java.util.Arrays;

public class PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        Arrays.sort(nums);
        return dfs(nums, 0, sum / 2);
    }

    public boolean dfs(int[] nums, int index, int target) {
        if (target == 0) {
            return true;
        }
        if (index == nums.length || target < 0) {
            return false;
        }
        if (dfs(nums, index + 1, target - nums[index])) {
            return true;
        }
        int j = index + 1;
        while (j < nums.length && nums[index] == nums[j]) {
            j++;
        }
        return dfs(nums, j, target);
    }

    public boolean canPartition_dp(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        // dp[i][j] = consider nums[0..i], with target sum as j, return true if exist.
        // i in [0.. n - 1], j in [0..target]
        // We want to know dp[n - 1][target];
        // dp[i][j] = dp[i - 1][j - nums[i]] || dp[i - 1][j]
        //                    put                 not put

        // Base case:
        // dp[0][j] = j == 0 || j == arr[0];
        // dp[i][0] = true;
        boolean[][] dp = new boolean[nums.length][target + 1];
        for (int i = 0; i < dp.length; ++i) {
            for (int j = 0; j < dp[0].length; ++j) {
                if (i == 0 && j > 0) {
                    dp[i][j] = nums[i] == j;
                } else if (j == 0) {
                    dp[i][j] = true;
                } else {
                    dp[i][j] = dp[i - 1][j]; // not put
                    if (j - nums[i] >= 0) {
                        dp[i][j] |= dp[i - 1][j - nums[i]]; // put
                    }
                }
            }
        }
        return dp[nums.length - 1][target];
    }

    public boolean canPartition_dp_betterSpace(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int i = 1; i < dp.length; ++i) {
            // If we only store one row, we have to do it from right to left.
            // Otherwise, dp[j - nums[i-1]] will not be the data in the previous row
            for (int j = target; j - nums[i- 1] >= 0; --j) {
                dp[j] |= dp[j - nums[i - 1]];
            }
        }
        return dp[target];
    }
}
