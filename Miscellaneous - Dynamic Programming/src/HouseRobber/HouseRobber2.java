package HouseRobber;

public class HouseRobber2 {
    public int robCircular(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int n = nums.length;
        // Return the maximum of two:
        // 1. rot the first one but not rob the last one.
        // 2. not rob the first one but rob the last one.
        return Math.max(simpleRob(nums, 0, n - 2), simpleRob(nums, 1, n - 1));
    }

    // This simple rob is the same as rob above, just we have specified the start and end positions
    public int simpleRob(int[] nums, int first, int last) {
        if (nums.length == 0) {
            return 0;
        }
        //                       prev2   prev1
        // Set up the base case: f(-1) = f(0) = 0
        int prev1 = 0;
        int prev2 = 0;
        for (int i = first; i <= last; ++i) {
            int cur = nums[i];
            int temp = prev1;
            // temp here is f(k - 1) and cur + prev2 here is f(k - 2) + Ak
            // move both prev1 and prev2 1 step forward
            prev1 = Math.max(temp, cur + prev2);
            prev2 = temp;
        }
        return prev1;
    }
}
