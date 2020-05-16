public class SearchInSortedArray {
    public int search(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int minIndex = findMinIndex(nums);
        if (target == nums[minIndex]) {
            return minIndex;
        }
        int m = nums.length;
        // Find the start and end position of our following binary search
        int start;
        int end;
        if (target <= nums[m - 1]) {
            // we have already checked index minIndex, so we can immediately exclude it
            start = minIndex + 1;
            end = m - 1;
        } else {
            start = 0;
            end = minIndex - 1;
        }
        // Now, do the binary search in the predefined range
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (target < nums[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }


    // Find the index of the minimum number
    private int findMinIndex(int[] nums) {
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > nums[end]) {
                // we already know nums[mid] cannot be the smallest since it's greater than something
                start = mid + 1;
            } else {
                // nums[end] may still be the smallest so we cannot just exclude it
                end = mid;
            }
        }
        // the smallest appears when start = end
        return start;
    }
}
