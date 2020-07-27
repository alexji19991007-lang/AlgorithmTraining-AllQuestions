// LeetCode 31
public class F047_NextPermutation {
    // TC: O(n)
    // SC: O(1)
    public void nextPermutation(int[] nums) {
        if (nums.length <= 1) {
            return;
        }
        // Find the first decreasing element from right to left
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        // If the array is non-decreasing from right to left, reverse the whole array
        if (i == -1) {
            reverse(nums, i + 1, nums.length - 1);
            return;
        }
        // Find the smallest (rightmost) element to the right of i that is just bigger than the first
        // decreasing element and swap the first decreasing one with that element
        int left = i + 1, right = nums.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= nums[i]) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        int indexToSwap = nums[right] > nums[i] ? right : left;
        swap(nums, i, indexToSwap);
        // reverse the subarray after i, and we get the next permutation (largest possible)
        reverse(nums, i + 1, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start++, end--);
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
