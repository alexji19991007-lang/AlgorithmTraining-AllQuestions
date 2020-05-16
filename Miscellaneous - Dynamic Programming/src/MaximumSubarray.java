public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int globalMax = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            // We use dynamic programming here. If the previous sum is not negative, then we can still add on.
            // If the previous sum is negative, we start over again
            if (nums[i - 1] > 0) {
                nums[i] += nums[i  - 1];
            }
            // Update global max accordingly.
            globalMax = Math.max(nums[i], globalMax);
        }
        return globalMax;
    }
}
