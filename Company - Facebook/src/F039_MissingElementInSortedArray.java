// LeetCode 1060
public class F039_MissingElementInSortedArray {
    // TC: O(n)
    // SC: O(1)
    public int missingElement(int[] nums, int k) {
        int n = nums.length;
        if (k > missing(n - 1, nums)) {
            return nums[n - 1] + (k - missing(n - 1, nums));
        }
        int left = 0, right = n - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (k > missing(mid, nums)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left - 1] + (k - missing(left - 1, nums));
    }

    public int missing(int index, int[] nums) {
        // nums[index]和nums[0]之间应该有nums[index] - nums[0] - 1个数字
        //                        实际有index - 0 - 1个数字
        // 所以缺少了nums[index] - nums[0] - index个数字
        return nums[index] - nums[0] - index;
    }
}
