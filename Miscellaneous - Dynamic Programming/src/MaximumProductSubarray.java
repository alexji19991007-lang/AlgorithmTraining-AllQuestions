public class MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        // We have to keep two records because a negative product could become very large if it is
        // multiplied by another negative number.
        int maxBefore = nums[0];
        int minBefore = nums[0];
        int curMax = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            int maxNow = Math.max(Math.max(maxBefore * nums[i], minBefore * nums[i]), nums[i]);
            int minNow = Math.min(Math.min(maxBefore * nums[i], minBefore * nums[i]), nums[i]);
            curMax = Math.max(curMax, maxNow);
            maxBefore = maxNow;
            minBefore = minNow;
        }
        return curMax;
    }
}
