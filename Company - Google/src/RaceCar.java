// LeetCode 818
public class RaceCar {
    // Suppose we issue m "A" instructions before issuing the 1st "R" instruction, we have proceeded by j units --> j = 2 ^ m - 1
    // Case 1 (j < i) -- "R" issued before we reach target i:
    //      Since we changed direction before reaching the target, a second "R" needs to be issued later
    //      Suppose we issue n "A" instructions before issuing the 2nd "R" instruction, we have returned by k units --> k = 2 ^ n - 1
    //      After the 2nd "R" instruction, we will start from j - k, and move to i --> It's defined by dp[i - (j - k)]
    //      Total # of Instructions X = m + 1 + n + 1 + dp[i - (j - k)], where the "+1" means the extra "R"
    //
    // Case 2 (j == i) -- Target reached without issuing "R"
    //      Total # of Instructions Y = m
    //
    // Case 3 (j > i) -- "R" issued after passing target i:
    //      Since we have already passed target i, we need to issue "R" and go backward
    //      We proceeded by j units, we need to return by (j - i) units
    //      Total # of Instructions Z = m + 1 + dp[j - i]
    //
    // dp[i] = Math.min(X, Y, Z)
    // TC: O(target * log(target) * log(target))
    // SC: O(target)
    public int racecar(int target) {
        int[] dp = new int[target + 1];
        for (int i = 1; i <= target; ++i) {
            dp[i] = Integer.MAX_VALUE;
            int m = 1;
            int j;
            // This is for Case 1
            for (j = 1; j < i; j = (1 << ++m) - 1) {
                int n = 0;
                for (int k = 0; k < j; k = (1 << ++n) - 1) {
                    dp[i] = Math.min(dp[i], m + 1 + n + 1 + dp[i - (j - k)]);
                }
            }
            // This is for Case 2 and Case 3
            dp[i] = Math.min(dp[i], m + (i == j ? 0 : 1 + dp[j - i]));
        }
        return dp[target];
    }
}
